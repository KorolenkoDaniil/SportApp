using FirebaseAdmin.Messaging;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using SportAppServer.Entities.context;
using SportAppServer.Entities.Pagination;

namespace SportAppServer.Controllers
{
    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {
       
        [HttpGet("GetNews")]
        public async Task<IActionResult> GetNews(int pageNumber = 1, int pageSize = 10)
        {
            using (var _dbContext = new NewsContext())
            {
                int totalItems = await _dbContext.News.CountAsync();


                var list = await _dbContext.News
                    .OrderByDescending(news => news.DateTime)
                    .Skip((pageNumber - 1) * pageSize)
                    .Take(pageSize)
                    .ToListAsync();


                var page = new NewsPagination
                {
                    PageNumber = pageNumber,
                    PageSize = pageSize,
                    TotalItems = totalItems,
                    News = list
                };



                return Json(page);
            }
        }



        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNews(string dateTime)
        {
            DateTime newsDateTime = DateTime.Parse(dateTime);

            using (var _dbContext = new NewsContext())
            {
                var news = await _dbContext.News
                .FirstOrDefaultAsync(item => item.DateTime == newsDateTime);

                if (news == null)
                {
                    return NotFound();
                }

                return Json(news);
            }
        }

    }
}
