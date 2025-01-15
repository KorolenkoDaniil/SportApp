using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SportAppServer.Entities;

namespace SportAppServer.Controllers
{

    [Route("NewsController")]
    [ApiController]
    public class NewsController : Controller
    {
        [HttpGet]
        public  string Get()
        {
            using (var db = new ApplicationContext())
            {

                List<News> list = db.News
                              .OrderByDescending(news => news.DateTime)
                              .Take(20)
                              .ToList();

                foreach (var item in list)
                {
                    Console.WriteLine(item.Title);
                }
                
                return JsonConvert.SerializeObject(list);
            }
        }
    }
}
