using Microsoft.AspNetCore.Mvc;
using SportAppServer.Models.DTOs;
using SportAppServer.Models.Pagination;
using SportAppServer.Services;

namespace SportAppServer.Controllers
{
    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {

        private readonly INewsService _newsService;

   
        public NewsController(INewsService newsService)
        {
            _newsService = newsService;
          
        }


        [HttpGet("GetNews")]
        public async Task<IActionResult> GetNews(int pageNumber = 1, int pageSize = 10)
        {
            NewsPagination paginatedNews = await _newsService.GetPaginatedNewsList(pageNumber, pageSize);

            if (paginatedNews.News.Count == 0)
            {
                return NotFound();
            }

            return Ok(paginatedNews);
        }




        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNews(string dateTime)
        {
            NewsDTO? news = await _newsService.GetNewsByDateAsync(dateTime);

            if (news == null)
            {
                return NotFound();
            }

            return Ok(news);
        }
    }
}

