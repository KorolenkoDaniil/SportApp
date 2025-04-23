using Microsoft.AspNetCore.Mvc;
using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;
using SportAppServer.Services;
using System.Diagnostics;


namespace SportAppServer.Controllers
{
    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {

        private readonly INewsService _newsService;
        private readonly ILikeServise _likeService;


        public NewsController(INewsService newsService, ILikeServise likeServise)
        {
            _newsService = newsService;
            _likeService = likeServise;
        }


        [HttpGet("GetNews")]
        public async Task<IActionResult> GetNews(int pageNumber = 1, int pageSize = 10)
        {
            Debug.WriteLine($"pageNumber {pageNumber} pageSize {pageSize}");

            NewsPagination paginatedNews = await _newsService.GetPaginatedNewsList(pageNumber, pageSize);

            if (paginatedNews.News.Count == 0)
            {
                return NotFound();
            }

            return Ok(paginatedNews);
        }




        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNews(string dateTime, string userEmail)
        {
            NewsDTO news = await _newsService.GetNewsByDateAsync(dateTime, userEmail);

            return Ok(news);
        }


        [HttpPost("AddLike")]
        public async Task<IActionResult> AddLike ([FromBody] LikeDto like)
        {
            if (like == null)
                return BadRequest();

            int likesCount = await _likeService.AddLikeAsync(like);

            if (likesCount < 0)
            {
                return BadRequest();
            }

            return Ok();
        }


        [HttpPost("RemoveLike")]
        public async Task<IActionResult> RemoveLike([FromBody] LikeDto like)
        {
            if (like == null)
                return BadRequest();

            int likesCount = await _likeService.RemoveLikeAsync(like);

            if (likesCount < 0)
            {
                return BadRequest();
            }

            return Ok();
        }



        [HttpPost("SearchNews")]
        public async Task<IActionResult> SearchNews([FromQuery] string searchPrompt, int pageNumber = 1, int pageSize = 10)
        {
            if (searchPrompt == null)
                return BadRequest();

            NewsPagination paginatedNews = await _newsService.GetPaginatedNewsListwithSearch(searchPrompt, pageNumber, pageSize);

            if (paginatedNews.News.Count == 0)
            {
               paginatedNews = await _newsService.GetPaginatedNewsList(pageNumber, pageSize);
            }

            return Ok(paginatedNews);
        }




    }
}

