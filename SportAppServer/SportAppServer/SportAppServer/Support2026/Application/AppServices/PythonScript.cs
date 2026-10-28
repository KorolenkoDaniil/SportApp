using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Caching.Distributed;
using Microsoft.Extensions.DependencyInjection;
using Newtonsoft.Json;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Mappers;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Services;
using SportAppServer.Support2026.Infrastructure.Database.Context;
using System.Diagnostics;
using System.Text;

namespace SportAppServer.Support2026.Application.AppServices
{
    public class PythonScript
    {
        private readonly IDistributedCache _distributedCache;
        private readonly IServiceScopeFactory _scopeFactory;

        public PythonScript(IDistributedCache distributedCache, IServiceScopeFactory scopeFactory)
        {
            _distributedCache = distributedCache;
            _scopeFactory = scopeFactory;
        }

        public async Task RunPythonScriptAsync()
        {
            try
            {
                // Очистка кэша Redis (обернута в try-catch на случай проблем с подключением)
                await _distributedCache.RemoveAsync("cachedNewsList");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Предупреждение Redis: не удалось очистить кэш: {ex.Message}");
            }

            try
            {
                Console.WriteLine("Python-скрипт начало");
                string pythonPath = @"C:\Users\korol\AppData\Local\Programs\Python\Python311\python.exe";
                string scriptPath = @"C:\Users\korol\AndroidStudioProjects\SportApp\SportAppServer\dataParser.py";

                ProcessStartInfo startInfo = new ProcessStartInfo
                {
                    FileName = pythonPath,
                    Arguments = scriptPath,
                    RedirectStandardOutput = true,
                    RedirectStandardError = true,
                    UseShellExecute = false,
                    CreateNoWindow = true
                };

                using (Process process = Process.Start(startInfo))
                {
                    if (process != null)
                    {
                        string output = await process.StandardOutput.ReadToEndAsync();
                        string error = await process.StandardError.ReadToEndAsync();
                        process.WaitForExit();

                        Console.WriteLine($"Python-скрипт завершён. Вывод: {output}");
                        if (!string.IsNullOrEmpty(error))
                        {
                            Console.WriteLine($"Ошибки: {error}");
                        }

                        await GetNewNewsFromJSONAsync();
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Ошибка запуска Python-скрипта:");
                Console.WriteLine($"Сообщение: {ex.Message}");
                Console.WriteLine($"Тип исключения: {ex.GetType().FullName}");
                Console.WriteLine($"Стек вызовов: {ex.StackTrace}");
            }
        }

        private async Task GetNewNewsFromJSONAsync()
        {
            string path = @"C:\Users\korol\AndroidStudioProjects\SportApp\SportAppServer\news_list.json";

            if (!File.Exists(path))
            {
                Console.WriteLine($"Файл JSON не найден по пути: {path}");
                return;
            }

            List<News>? newsList = new List<News>();

            using (StreamReader reader = new StreamReader(path, Encoding.UTF8))
            {
                string text = await reader.ReadToEndAsync();
                Console.WriteLine("--- JSON content read by C# ---");
                Console.WriteLine(text);
                Console.WriteLine("--- End JSON content ---");

                try
                {
                    List<NewsParsingDto>? newsDtoList = JsonConvert.DeserializeObject<List<NewsParsingDto>>(text);

                    if (newsDtoList != null)
                    {
                        newsList = newsDtoList
                            .Where(dto => dto != null)
                            .Select(dto => NewsMapper.MapToEntityFromParsingDto(dto))
                            .Where(entity => entity != null)
                            .ToList()!;
                    }
                }
                catch (JsonException jsonEx)
                {
                    Console.WriteLine($"JSON Deserialization Error: {jsonEx.Message}");
                    newsList = null;
                }
            }

            if (newsList == null || newsList.Count == 0)
            {
                Console.WriteLine("Список новостей пуст или не удалось десериализовать данные.");
                return;
            }

            using (var scope = _scopeFactory.CreateScope())
            {
                var context = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();

                try
                {
                    List<News> newNews = newsList
                        .Where(n => !context.NewsList.Any(existingNews => existingNews.DateTime == n.DateTime))
                        .ToList();

                    Debug.WriteLine("---111---");
                    foreach (var news in newNews)
                    {
                        Debug.WriteLine(news?.ToString() ?? "null");
                    }
                    Debug.WriteLine("---111---");

                    if (newNews.Count == 0)
                    {
                        Console.WriteLine("Нет новых новостей для добавления.");
                        return;
                    }

                    newNews = NullFilterServise.FilterNews(newNews);

                    Debug.WriteLine("---222---");
                    foreach (var news in newNews)
                    {
                        Debug.WriteLine(news?.ToString() ?? "null");
                    }
                    Debug.WriteLine("---222---");

                    await context.NewsList.AddRangeAsync(newNews);
                    await context.SaveChangesAsync();

                    List<News> last30News = await context.NewsList
                        .OrderByDescending(n => n.DateTime)
                        .Take(30)
                        .ToListAsync();

                    Debug.WriteLine("---444---");
                    foreach (var news in last30News)
                    {
                        Debug.WriteLine(news?.ToString() ?? "null");
                    }
                    Debug.WriteLine("---444---");

                    try
                    {
                        await _distributedCache.SetStringAsync("cachedNewsList", JsonConvert.SerializeObject(last30News));
                    }
                    catch (Exception redisEx)
                    {
                        Console.WriteLine($"Не удалось записать в кэш Redis: {redisEx.Message}");
                    }

                    Console.WriteLine($"Успешно добавлено {newNews.Count} новостей в базу данных.");
                }
                catch (DbUpdateException ex)
                {
                    Console.WriteLine(ex.InnerException?.Message ?? ex.Message);
                }
            }
        }
    }
}