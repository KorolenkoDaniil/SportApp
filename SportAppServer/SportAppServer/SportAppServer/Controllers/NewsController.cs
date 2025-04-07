using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
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
            using var _dbContext = new DBContext();
            int totalItems = await _dbContext.News.CountAsync();


            var newsList = await _dbContext.News
                .Include(n => n.NewsTags)
                .Include(n => n.NewsComments)
                .OrderByDescending(news => news.DateTime)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();

          
            var page = new NewsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                News = newsList
            };

            string json = JsonConvert.SerializeObject(page, new JsonSerializerSettings
            {
                Formatting = Formatting.Indented,
                ReferenceLoopHandling = ReferenceLoopHandling.Ignore
            });

            return Content(json, "application/json");
        }



        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNews(string dateTime)
        {
            DateTime newsDateTime = DateTime.Parse(dateTime);

            using (var _dbContext = new DBContext())
            {
                var news = await _dbContext.News
                    .Include(n => n.NewsTags)
                    .FirstOrDefaultAsync(item => item.DateTime == newsDateTime);

                if (news == null)
                {
                    return NotFound();
                }

                string json = JsonConvert.SerializeObject(news, new JsonSerializerSettings
                {
                    Formatting = Formatting.Indented,
                    ReferenceLoopHandling = ReferenceLoopHandling.Ignore
                });

                return Content(json, "application/json");
            }
        }


    }
}
