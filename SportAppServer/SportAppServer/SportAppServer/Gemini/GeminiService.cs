using Newtonsoft.Json.Linq;
using SportAppServer.Gemini.DTO;
using SportAppServer.Models.DTOs;
using System.Diagnostics;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;

namespace SportAppServer.Gemini
{
    public class GeminiService : IGeminiService
    {
        private static readonly string apiKey = Environment.GetEnvironmentVariable("GEMINI_API_KEY")!;
        private static readonly string apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

        // Универсальный HttpClient с таймаутом и поддержкой сжатия
        private static readonly HttpClient _httpClient = new HttpClient
        {
            Timeout = TimeSpan.FromSeconds(30)
        };

        public async Task<AIMessageDto> AskGemini(GeminiDTORequest prompt, string email)
        {
            var requestBody = BuildRequestBody(prompt.Prompt);
            string response = await SendRequestToGeminiAsync(requestBody);

            JObject json = JObject.Parse(response);
            string text = (string?)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"] ?? "Ответ не найден";

            return new AIMessageDto(email, text, true);
        }

        public async Task<List<string>> CreateTags(string newsText)
        {
            if (string.IsNullOrWhiteSpace(newsText))
                throw new ArgumentException("Текст новости не может быть пустым");

            var requestBody = BuildRequestBody($"придумай 5 коротких тегов для этой новости, разделённых %, без хэштегов. Новость: {newsText}");
            string response = await SendRequestToGeminiAsync(requestBody);

            JObject json = JObject.Parse(response);
            string tagResponse = (string?)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"] ?? "";

            return tagResponse.Split('%', StringSplitOptions.RemoveEmptyEntries)
                              .Select(tag => tag.Trim())
                              .ToList();
        }

        private async Task<string> SendRequestToGeminiAsync(object requestBody)
        {
            string requestUrl = $"{apiUrl}?key={apiKey}";
            string jsonRequest = JsonSerializer.Serialize(requestBody);

            using var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");

            Debug.WriteLine("⏱ Отправка запроса в Gemini...");
            var sw = Stopwatch.StartNew();

            var response = await _httpClient.PostAsync(requestUrl, content);

            Debug.WriteLine($"✅ Ответ от Gemini через {sw.ElapsedMilliseconds} ms");

            response.EnsureSuccessStatusCode();
            string result = await response.Content.ReadAsStringAsync();
            Debug.WriteLine($"📦 JSON получен, длина: {result.Length}");

            return result;
        }

        private object BuildRequestBody(string text)
        {
            return new
            {
                contents = new[]
                {
                    new
                    {
                        parts = new[]
                        {
                            new { text }
                        }
                    }
                }
            };
        }
    }
}
