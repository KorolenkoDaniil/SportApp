using Newtonsoft.Json.Linq;
using System.Diagnostics;
using System.Text;
using System.Text.Json;

namespace SportAppServer.Entities
{
    public static class Gemini
    {

        private static readonly string apiKey = Environment.GetEnvironmentVariable("GEMINI_API_KEY")!;
        private static readonly string apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

        public static async Task<List<string>> CreateTags(string newsText)
        {
            if (string.IsNullOrEmpty(newsText))
                throw new ArgumentException("Текст новости не может быть пустым");

            using HttpClient client = new();
            string requestUrl = $"{apiUrl}?key={apiKey}";

            var requestBody = new
            {
                contents = new[]
                {
                    new { parts = new[] { new { text = $"придумай 5 коротких тегов для этой новости ответ прішлі в формате tag1%tag2%tag3 . Новость: {newsText}" } } }
                }
            };

            string jsonRequest = JsonSerializer.Serialize(requestBody);
            var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");



            HttpResponseMessage response = await client.PostAsync(requestUrl, content);
            string jsonResponse = await response.Content.ReadAsStringAsync();

            JObject json = JObject.Parse(jsonResponse);
            string responseWithTags = (string)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"]!;

            Debug.WriteLine(responseWithTags);

            List<string> tags = responseWithTags.Split("%").ToList();

            Debug.WriteLine(tags);
            return tags;
        }
    }
}



//System.NullReferenceException: "Object reference not set to an instance of an object."

//responseWithTags было null.
