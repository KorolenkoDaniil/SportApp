using Microsoft.AspNetCore.Mvc;
using SportAppServer.Entities;

namespace SportAppServer.Controllers
{

    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {
        [HttpGet]
        public  IActionResult Get()
        {
            using (var db = new ApplicationContext())
            {

                List<News> list = db.News
                              .OrderByDescending(news => news.DateTime)
                              .Take(20)
                              .ToList();

               
                return Json(list);
            }
        }
    }
}
