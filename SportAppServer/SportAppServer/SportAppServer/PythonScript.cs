using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using SportAppServer.Entities;
using System.Diagnostics;

namespace SportAppServer
{
    public class PythonScript
    {
        public async Task RunPythonScriptAsync()
        {
            try
            {
                Console.WriteLine($"Python-скрипт начало");
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
                        string output = process.StandardOutput.ReadToEnd();
                        string error = process.StandardError.ReadToEnd();
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
            string path = "C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\news_list.json";

            List<News>? newsList;

  
            using (StreamReader reader = new StreamReader(path))
            {
                string text = await reader.ReadToEndAsync();
                Console.WriteLine(text);

                newsList = JsonConvert.DeserializeObject<List<News>>(text);
            }

            if (newsList == null || !newsList.Any())
            {
                Console.WriteLine("Список новостей пуст или не удалось десериализовать данные.");
                return;
            }

            using (var newsDB = new NewsContext())
            {
                try
                {
                    await newsDB.AddNewsToDBAsync(newsList);
                    await newsDB.SaveChangesAsync();
                }
                catch (DbUpdateException ex)
                {
                    if (ex.InnerException != null)
                    {
                        Console.WriteLine($"Inner Exception: {ex.InnerException.Message}");
                    }
                    else
                    {
                        Console.WriteLine($"Error: {ex.Message}");
                    }
                }

            }
        }
    }
}
