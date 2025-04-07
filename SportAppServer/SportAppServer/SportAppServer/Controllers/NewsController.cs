using Microsoft.AspNetCore.Mvc;
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
            string json = await _newsService.GetPaginatedNewsList(pageNumber, pageSize);

            return Content(json, "application/json");
        }



        [HttpGet("GetOneNews")]
        public async Task<IActionResult> GetOneNews(string dateTime)
        {
            //DateTime newsDateTime = DateTime.Parse(dateTime);

            //using (var _dbContext = new DBContext())
            //{
            //    var news = await _dbContext.News
            //        .Include(n => n.NewsTags)
            //        .Include(n => n.NewsComments)
            //        .FirstOrDefaultAsync(item => item.DateTime == newsDateTime);

            //    if (news == null)
            //    {
            //        return NotFound();
            //    }

            //    string json = JsonConvert.SerializeObject(news, new JsonSerializerSettings
            //    {
            //        Formatting = Formatting.Indented,
            //        ReferenceLoopHandling = ReferenceLoopHandling.Ignore
            //    });

            //    return Content(json, "application/json");
            //}

            return Ok();
        }


    }
}
