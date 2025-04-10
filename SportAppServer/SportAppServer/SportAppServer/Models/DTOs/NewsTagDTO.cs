using Newtonsoft.Json;
using SportAppServer.Models.Entities;

namespace SportAppServer.Models.DTOs
{
    public class NewsTagDTO
    {

        [JsonProperty("tag")]
        public string Tag { get; set; }

        [JsonProperty("news_date_time")]
        public DateTime NewsDateTime { get; set; }


        public NewsTagDTO(string tag, DateTime newsDateTime)
        {
            Tag = tag;
            NewsDateTime = newsDateTime;
        }

        public override string ToString()
        {
            return $"Tag: {Tag}, NewsId: {NewsDateTime}";
        }
    }
}











//using (var context = new ApplicationDbContext())
//{
//    // Создаем новость
//    var news = new News
//    {
//        DateTime = DateTime.Now,
//        Sport = "Теннис",
//        Title = "Соболенко вышла в четвертьфинал",
//        ImageId = "image123",
//        ArticleText = "Текст новости..."
//    };

//    // Добавляем теги к новости
//    news.NewsTags.Add(new NewsTag { Tag = "теннис" });
//    news.NewsTags.Add(new NewsTag { Tag = "Соболенко" });
//    news.NewsTags.Add(new NewsTag { Tag = "WTA" });

//    // Добавляем новость в контекст
//    context.News.Add(news);

//    // Сохраняем изменения в базе данных
//    context.SaveChanges();
//}




//using (var context = new ApplicationDbContext())
//{
//    // Получаем новость с тегами
//    var newsWithTags = context.News
//        .Include(n => n.NewsTags) // Загружаем связанные теги
//        .FirstOrDefault(n => n.DateTime == newsDateTime);

//    if (newsWithTags != null)
//    {
//        Console.WriteLine($"Новость: {newsWithTags.Title}");
//        Console.WriteLine("Теги:");
//        foreach (var tag in newsWithTags.NewsTags)
//        {
//            Console.WriteLine($"- {tag.Tag}");
//        }
//    }
//}






//using (var context = new ApplicationDbContext())
//{
//    // Находим новость
//    var news = context.News
//        .Include(n => n.NewsTags)
//        .FirstOrDefault(n => n.DateTime == newsDateTime);

//    if (news != null)
//    {
//        // Очищаем старые теги
//        news.NewsTags.Clear();

//        // Добавляем новые теги
//        news.NewsTags.Add(new NewsTag { Tag = "новый тег 1" });
//        news.NewsTags.Add(new NewsTag { Tag = "новый тег 2" });

//        // Сохраняем изменения
//        context.SaveChanges();
//    }
//}



//using (var context = new ApplicationDbContext())
//{
//    // Находим новость
//    var news = context.News
//        .Include(n => n.NewsTags)
//        .FirstOrDefault(n => n.DateTime == newsDateTime);

//    if (news != null)
//    {
//        // Удаляем новость (теги удалятся автоматически)
//        context.News.Remove(news);

//        // Сохраняем изменения
//        context.SaveChanges();
//    }
//}