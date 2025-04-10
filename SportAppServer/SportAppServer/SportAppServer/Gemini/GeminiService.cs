using Newtonsoft.Json.Linq;
using SportAppServer.Gemini.Models;
using System.Diagnostics;
using System.Text;
using System.Text.Json;

namespace SportAppServer.Gemini
{
    public class GeminiService : IGeminiService
    {
        private static readonly string apiKey = Environment.GetEnvironmentVariable("GEMINI_API_KEY")!;
        private static readonly string apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";


        public async Task<GeminiDTOResponse> AskGemini(GeminiDTORequest prompt)
        {
            var requestBody = _BuildRequestBody(prompt.Prompt);

            string response = await _SendRequestToGemini(requestBody);

            JObject json = JObject.Parse(response);
            string text = (string?)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"] ?? "Ответ не найден";

            return new GeminiDTOResponse(text);
        }

        public async Task<List<string>> CreateTags(string newsText)
        {
            if (string.IsNullOrWhiteSpace(newsText))
                throw new ArgumentException("Текст новости не может быть пустым");

            var requestBody = _BuildRequestBody($"придумай 5 коротких тегов для этой новости, разделённых %, без хэштегов. Новость: {newsText}");



            string response = await _SendRequestToGemini(requestBody);

            JObject json = JObject.Parse(response);
            string tagResponse = (string?)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"] ?? "";

            List<string> tags = tagResponse.Split('%', StringSplitOptions.RemoveEmptyEntries)
                                            .Select(tag => tag.Trim())
                                            .ToList();

            Debug.WriteLine($"Сгенерированные теги: {string.Join(", ", tags)}");

            return tags;
        }

        private async Task<string> _SendRequestToGemini(object requestBody)
        {
            using HttpClient client = new HttpClient();
            string requestUrl = $"{apiUrl}?key={apiKey}";

            string jsonRequest = JsonSerializer.Serialize(requestBody);
            var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(requestUrl, content);
            response.EnsureSuccessStatusCode(); // выбросит исключение если ошибка

            return await response.Content.ReadAsStringAsync();
        }


        private object _BuildRequestBody(string text)
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
          


