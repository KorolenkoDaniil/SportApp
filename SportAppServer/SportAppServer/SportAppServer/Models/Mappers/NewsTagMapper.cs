using SportAppServer.Entities.Models;
using SportAppServer.Models.DTOs;


namespace SportAppServer.Models.Mappers
{
    public static class NewsTagMapper
    {
        public static NewsTagDTO ConvertToDTO(NewsTag tag)
        {
            return new NewsTagDTO(
                tag.Tag,
                tag.NewsDateTime
            );
        }

        public static List<NewsTagDTO> ConvertToListOfDTO(List<NewsTag> tags)
        {
            List<NewsTagDTO> newsTagDTOs = new List<NewsTagDTO>();

            foreach (var item in tags)
            {
                newsTagDTOs.Add(new NewsTagDTO(
                    item.Tag,
                    item.NewsDateTime
                    )
                );
            };

            return newsTagDTOs;
        }
    }
}
