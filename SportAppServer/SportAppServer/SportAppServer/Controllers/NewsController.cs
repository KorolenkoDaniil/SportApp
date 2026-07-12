using Microsoft.AspNetCore.Mvc;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination;
using SportAppServer.Support2026.Application.Feature.NewsFeature.UseCases;
using System.Diagnostics;


namespace SportAppServer.Controllers
{
    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {
        private GetPaginatedNewsUseCase _getPaginatedNewsUseCase;
        private GetNewsByIdUseCase _getNewsByIdUseCase;

        //private readonly ILikeServise _likeService;


        //public NewsController(INewsService newsService, ILikeServise likeServise)
        //{
        //    _newsService = newsService;
        //    _likeService = likeServise;
        //}

        public NewsController(GetPaginatedNewsUseCase getPaginatedNewsUseCase, GetNewsByIdUseCase getNewsByDateUseCase)
        {

            //обьединиить множестов use cases В ФАСАД
            _getPaginatedNewsUseCase = getPaginatedNewsUseCase;
            _getNewsByIdUseCase = getNewsByDateUseCase;
        }


        [HttpGet("GetNews")]
        public async Task<IActionResult> GetNews(int pageNumber = 1, int pageSize = 10)
        {
            try
            {
                Debug.WriteLine($"[GetNews] pageNumber: {pageNumber}, pageSize: {pageSize}");

                PaginatedList<NewsApiDto> paginatedNews = await _getPaginatedNewsUseCase.Execute(pageNumber, pageSize);

                if (paginatedNews.ItemsList == null)
                {
                    Debug.WriteLine("[GetNews] paginatedNews.messages is null");
                    return StatusCode(500, "Ошибка: данные не получены");
                }

                if (paginatedNews.ItemsList.Count == 0)
                {
                    Debug.WriteLine("[GetNews] Нет новостей");
                    return NotFound();
                }

                Debug.WriteLine($"[GetNews] Успешно возвращено новостей: {paginatedNews.ItemsList.Count}");
                Debug.WriteLine($"[GetNews] Успешно возвращено новостей: {paginatedNews.ItemsList.Count}");

                return Ok(paginatedNews);
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"[GetNews] Ошибка: {ex.Message}");
                Debug.WriteLine(ex.StackTrace);
                return StatusCode(500, "Внутренняя ошибка сервера");
            }
        }





        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNewsByID(int newsId)
        {
            try
            {
                Debug.WriteLine($"[GetOneNewsById] newsId: {newsId}");

                PaginatedList<NewsApiDto> paginatedNews = await _getNewsByIdUseCase.Execute(newsId);

                if (paginatedNews.ItemsList == null)
                {
                    Debug.WriteLine("[GetOneNewsByDate] paginatedNews.messages is null");
                    return StatusCode(500, "Ошибка: данные не получены");
                }

                if (paginatedNews.ItemsList.Count == 0)
                {
                    Debug.WriteLine("[GetOneNewsByDate] Нет новостей");
                    return NotFound();
                }

                Debug.WriteLine($"[GetOneNewsByDate] Успешно возвращено новостей: {paginatedNews.ItemsList.Count}");

                return Ok(paginatedNews);
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"[GetOneNewsByDate] Ошибка: {ex.Message}");
                Debug.WriteLine(ex.StackTrace);
                return StatusCode(500, "Внутренняя ошибка сервера");
            }
        }


        //[HttpPost("AddLike")]
        //public async Task<IActionResult> AddLike ([FromBody] LikeDto like)
        //{
        //    if (like == null)
        //        return BadRequest();

        //    int likesCount = await _likeService.AddLikeAsync(like);

        //    if (likesCount < 0)
        //    {
        //        return BadRequest();
        //    }

        //    return Ok();
        //}


        //[HttpPost("RemoveLike")]
        //public async Task<IActionResult> RemoveLike([FromBody] LikeDto like)
        //{
        //    if (like == null)
        //        return BadRequest();

        //    int likesCount = await _likeService.RemoveLikeAsync(like);

        //    if (likesCount < 0)
        //    {
        //        return BadRequest();
        //    }

        //    return Ok();
        //}


        //[HttpGet ("SearchNews")]
        //public async Task<IActionResult> SearchNews(string? searchPrompt, int pageSize, int pageNumber, int sportIndex)
        //{
        //    try
        //    {
        //        var result = await _newsService.GetPaginatedNewsListwithSearch(searchPrompt, pageSize, pageNumber, sportIndex);
        //        return Ok(result);
        //    }
        //    catch (Exception ex)
        //    {
        //        return StatusCode(500, new { error = ex.Message });
        //    }
        //}

    }
}

