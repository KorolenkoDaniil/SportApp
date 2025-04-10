using Microsoft.AspNetCore.Mvc;
using SportAppServer.Gemini;
using SportAppServer.Gemini.Models;


namespace SportAppServer.Controllers
{
    [Route("gemini")]
    [ApiController]
    public class GeminiController : Controller
    {

        private readonly IGeminiService _geminiService;
      

        public GeminiController(IGeminiService geminiService)
        {
            _geminiService = geminiService;
        }

        [HttpPost("ask")]
        public async Task<IActionResult> AskGemini([FromBody] GeminiDTORequest prompt)
        {
            //if (string.IsNullOrEmpty(apiKey))
            //    return StatusCode(500, new { answer = "AI на данный момент не доступен" });

            if (string.IsNullOrEmpty(prompt.Prompt))
                return BadRequest(new { answer = "Введите текст запроса" });


            GeminiDTOResponse response = await _geminiService.AskGemini(prompt);

            if (response == null)
            {
                return BadRequest(new { answer = "Something went wrong" });
            }
            else
            {
                return Ok(response);
            }
        }
    }
}