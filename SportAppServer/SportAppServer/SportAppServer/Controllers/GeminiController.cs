//using Microsoft.AspNetCore.Mvc;
//using SportAppServer.Gemini;
//using SportAppServer.Gemini.DTO;
//using SportAppServer.Models.DTOs;
//using SportAppServer.Models.NewsPagination;
//using SportAppServer.Services;
//using System.Diagnostics;

//namespace SportAppServer.Controllers
//{
//    [Route("gemini")]
//    [ApiController]
//    public class GeminiController : Controller
//    {

//        private readonly IGeminiService _geminiService;
//        private readonly IMessageService _messageService;


//        public GeminiController(IGeminiService geminiService, IMessageService messageService)
//        {
//            _geminiService = geminiService;
//            _messageService = messageService;
//        }


//        //[HttpGet("GetMessages")]
//        //public async Task<IActionResult> GetMessages(string email, int pageNumber = 1, int pageSize = 10)
//        //{
//        //    try
//        //    {
//        //        Debug.WriteLine($"[GetMesages] pageNumber: {pageNumber}, pageSize: {pageSize}, email {email}");

//        //        MessagesPagination paginatedMessages = await _messageService.GetPaginatedMessagesList(email, pageNumber, pageSize);

//        //        if (paginatedMessages.messages == null)
//        //        {
//        //            Debug.WriteLine("[GetMesages] paginatedMessages.messages is null");
//        //            return StatusCode(500, "Ошибка: данные не получены");
//        //        }

//        //        if (paginatedMessages.messages.Count == 0)
//        //        {
//        //            Debug.WriteLine("[GetMesages] Нет сооьбщений");
//        //            return NotFound();
//        //        }


//        //        return Ok(paginatedMessages);
//        //    }
//        //    catch (Exception ex)
//        //    {
//        //        Debug.WriteLine($"[GetMesages] Ошибка: {ex.Message}");
//        //        Debug.WriteLine(ex.StackTrace);
//        //        return StatusCode(500, "Внутренняя ошибка сервера");
//        //    }
//        //}



//        [HttpGet("GetMessages")]
//        public async Task<IActionResult> GetMessages(string email, int pageNumber = 1, int pageSize = 10)
//        {
//            try
//            {
//                Debug.WriteLine($"[GetMesages] pageNumber: {pageNumber}, pageSize: {pageSize}, email {email}");

//                MessagesPagination paginatedMessages = await _messageService.GetPaginatedMessagesList(email, pageNumber, pageSize);

//                return Ok(paginatedMessages);
//            }
//            catch (Exception ex)
//            {
//                Debug.WriteLine($"[GetMesages] Ошибка: {ex.Message}");
//                Debug.WriteLine(ex.StackTrace);
//                return StatusCode(500, "Внутренняя ошибка сервера");
//            }
//        }




//        [HttpPost("ask")]
//        public async Task<IActionResult> AskGemini([FromBody] GeminiDTORequest prompt)
//        {

//            Debug.WriteLine($"[AskGemini] {prompt.Email} {prompt.Prompt}");

//            if (string.IsNullOrEmpty(prompt.Prompt))
//                return BadRequest(new { answer = "Введите текст запроса" });

//            var sw = Stopwatch.StartNew(); // ⏱ старт замера времени

//            try
//            {
//                await _messageService.AddMessage(prompt.Prompt, prompt.Email, false);
//                Debug.WriteLine($"User message saved in {sw.ElapsedMilliseconds} ms");

//                MessageDTO response = await _geminiService.AskGemini(prompt, prompt.Email);
//                Debug.WriteLine($"Gemini response received in {sw.ElapsedMilliseconds} ms");

//                if (response == null)
//                    return StatusCode(500, new { answer = "AI не дал ответ" });

//                await _messageService.AddMessage(response.MessageText, prompt.Email, true);
//                Debug.WriteLine($"AI message saved in {sw.ElapsedMilliseconds} ms");

//                sw.Stop();
//                Debug.WriteLine($"Total time: {sw.ElapsedMilliseconds} ms");

//                return Ok(response);
//            }
//            catch (Exception ex)
//            {
//                sw.Stop();
//                Debug.WriteLine($"Error after {sw.ElapsedMilliseconds} ms: {ex.Message}");
//                return StatusCode(500, new { answer = "Ошибка при обработке запроса", error = ex.Message });
//            }
//        }

//    }
//}