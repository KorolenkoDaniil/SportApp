using Microsoft.AspNetCore.Mvc;
using SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.UseCases;
using SportAppServer.Support2026.Application.Feature.NewsLikeFeature.UseCases;
using SportAppServer.Support2026.Application.Feature.Pagination;
using System.Diagnostics;


namespace SportAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NewsController : Controller
    {
        private readonly GetPaginatedNewsUseCase _getPaginatedNewsUseCase;
        private readonly GetNewsDetailsUseCase _getNewsDetailsUseCase;
        private readonly CheckIfUserLikedNewsUseCase _checkIfUserLikedNewsUseCase;
        private readonly GetCommentsAmountUseCase _getCommentsAmountUseCase;
        private readonly GetLikesAmountUseCase _getLikesAmountUseCase;

        //private readonly ILikeServise _likeService;


        //public NewsController(INewsService newsService, ILikeServise likeServise)
        //{
        //    _newsService = newsService;
        //    _likeService = likeServise;
        //}

        public NewsController(
            GetPaginatedNewsUseCase getPaginatedNewsUseCase,
            GetNewsDetailsUseCase getNewsByDateUseCase,
            CheckIfUserLikedNewsUseCase checkIfUserLikedNewsUseCase,
            GetCommentsAmountUseCase getCommentsAmountUseCase,
            GetLikesAmountUseCase getLikesAmountUseCase
            )
        {

            //обьединиить множестов use cases В ФАСАД
            //бъединять их в один сервис имеет смысл только если конструктор разрастется до 5 + зависимостей.
            _getPaginatedNewsUseCase = getPaginatedNewsUseCase;
            _getNewsDetailsUseCase = getNewsByDateUseCase;
            _checkIfUserLikedNewsUseCase = checkIfUserLikedNewsUseCase;
            _getCommentsAmountUseCase = getCommentsAmountUseCase;
            _getLikesAmountUseCase = getLikesAmountUseCase;
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

                return Ok(paginatedNews);
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"[GetNews] Ошибка: {ex.Message}");
                Debug.WriteLine(ex.StackTrace);
                return StatusCode(500, "Внутренняя ошибка сервера");
            }
        }




        [HttpGet("GetNewsDetails/{newsId}")]
        public async Task<IActionResult> GetNewsDetails(
            [FromRoute] int newsId,
            [FromQuery] string? email = null) 
        {
            try
            {

                var newsDetails = await _getNewsDetailsUseCase.Execute(newsId);

                if (newsDetails == null)
                {
                    throw new ArgumentNullException("Ошибка GetNewsDetails новость не найденаЫ");
                }


                newsDetails.CommentsAmount = await _getCommentsAmountUseCase.Execute(newsId);
                newsDetails.LikesAmount = await _getLikesAmountUseCase.Execute(newsId);

                if (newsDetails == null)
                    return NotFound("Ошибка: данные не получены");


                


                // Если email передан — проверяем лайк, если нет — оставляем false
                if (!string.IsNullOrEmpty(email))
                {
                    newsDetails.isLiked = await _checkIfUserLikedNewsUseCase.ExecuteAsync(newsId, email);
                }

                return Ok(newsDetails);
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"[GetNewsDetails] Ошибка: {ex.Message}");
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

