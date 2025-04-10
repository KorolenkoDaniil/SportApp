using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;

namespace SportAppServer.Models.Mappers
{
    public static class NewsMapper
    {
        public static NewsDTO ConvertToDTO(News news)
        {
            return new NewsDTO(
                news.DateTime,
                news.Sport,
                news.Title,
                news.ImageId,
                news.ArticleText,
                NewsTagMapper.ConvertToListOfDTO(news.tags)
            );
        }


        public static List<NewsDTO> ConvertToListOfDTO(List<News> news)
        {
            List<NewsDTO> newsDTOs = new List<NewsDTO>();

            foreach (var item in news)
            {
                newsDTOs.Add(new NewsDTO(
                        item.DateTime,
                        item.Sport,
                        item.Title,
                        item.ImageId,
                        item.ArticleText,
                        NewsTagMapper.ConvertToListOfDTO(item.tags)
                    )
                );
            };

            return newsDTOs;
        }
    }
}
