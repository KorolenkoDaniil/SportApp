

using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;

namespace SportAppServer.Support2026.Domain.Feature.NewsFeature.Services
{
    public static class NullFilterServise
    {
        public static List<News> FilterNews(List<News> newsList)
        {
            List<News> filtered = new List<News>();

            foreach (var n in newsList)
            {
                // Если дата невалидная — пропускаем
                if (n.DateTime == DateTime.MinValue)
                    continue;

                // Если нет заголовка — пропускаем
                if (string.IsNullOrWhiteSpace(n.Title))
                    continue;

                // Если нет текста — пропускаем
                if (string.IsNullOrWhiteSpace(n.ArticleText))
                    continue;

                // Если нет спорта — пропускаем
                if (string.IsNullOrWhiteSpace(n.Sport))
                    continue;

                // Остальные поля — заменяем null на ""
                n.ImageId = n.ImageId ?? "";
                n.TextAfterLemmatize = n.TextAfterLemmatize ?? "";
                n.ArticleText = n.ArticleText ?? "";
                n.Sport = n.Sport ?? "";
                n.Title = n.Title ?? "";

                // Если Tags = null — создаём пустой список
                //n.Tags ??= new List<NewsTag>();

                filtered.Add(n);
            }

            return filtered;
        }
    }

}
