using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using SportAppServer.Entities;

[Route("NewsController")]
[ApiController]
public class NewsController : Controller
{
    [HttpGet("GetNews")]
    public string Get()
    {
        using (var db = new NewsContext())
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

    [HttpGet("GetOneNews")]
    public string GetOneNews(string dateTime)
    {
        using (var db = new NewsContext())
        {
            DateTime newsDateTime = DateTime.Parse(dateTime);

            News news = db.News.FirstOrDefault(item => item.DateTime == newsDateTime);

            return JsonConvert.SerializeObject(news);
        }
    }
}
