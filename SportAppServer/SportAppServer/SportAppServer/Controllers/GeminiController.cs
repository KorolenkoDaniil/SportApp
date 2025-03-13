using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json.Linq;
using SportAppServer.Entities.Models.dto;
using System.Text;
using System.Text.Json;

namespace SportAppServer.Controllers
{
    [Route("gemini/")]
    [ApiController]
    public class GeminiController : Controller
    {
        private static readonly string apiKey = Environment.GetEnvironmentVariable("GEMINI_API_KEY")!;
        private static readonly string apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";


        [HttpPost("ask")]
        public async Task<IActionResult> AskGemini([FromBody] PromptDto prompt)
        {
            if (string.IsNullOrEmpty(apiKey))
                return StatusCode(500, new { answer = "AI на данный момент не доступен" });

            if (string.IsNullOrEmpty(prompt.Prompt))
                return BadRequest(new { answer = "Введите текст запроса"} );

            bool isSportsRequest = await CheckIfSportsRelated(prompt.Prompt);

            if (!isSportsRequest)
                return Ok(new { answer = "Я могу отвечать только на вопросы по спорту." });


            string response = await SendRequestToGemini(prompt.Prompt);

            JObject json = JObject.Parse(response);
            string text = (string)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"]!;
  

            return Ok(new { answer = text });
        }


        private async Task<bool> CheckIfSportsRelated(string prompt)
        {
            using HttpClient client = new HttpClient();
            string requestUrl = $"{apiUrl}?key={apiKey}";

            var requestBody = new
            {
                contents = new[]
                {
                    new { parts = new[] { new { text = $"Этот вопрос связан со спортом? Ответь только 'да' или 'нет'. Вопрос: {prompt}" } } }
                }
            };

            string jsonRequest = JsonSerializer.Serialize(requestBody);
            var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(requestUrl, content);
            string jsonResponse = await response.Content.ReadAsStringAsync();

            JObject json = JObject.Parse(jsonResponse);
            string answer = (string)json["candidates"]?[0]?["content"]?["parts"]?[0]?["text"];

            return answer?.Trim().ToLower() == "да";
        }


        private async Task<string> SendRequestToGemini(string prompt)
        {
            using HttpClient client = new HttpClient();
            string requestUrl = $"{apiUrl}?key={apiKey}";

            var requestBody = new
            {
                contents = new[] { new { parts = new[] { new { text = prompt } } } }
            };

            string jsonRequest = JsonSerializer.Serialize(requestBody);
            var content = new StringContent(jsonRequest, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await client.PostAsync(requestUrl, content);
            return await response.Content.ReadAsStringAsync();
        }












    }
}