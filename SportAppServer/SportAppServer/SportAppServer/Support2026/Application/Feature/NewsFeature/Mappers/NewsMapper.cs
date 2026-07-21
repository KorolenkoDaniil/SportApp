using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;

namespace SportAppServer.Support2026.Application.Feature.NewsFeature.Mappers
{
    public static class NewsMapper
    {

       

        public static News MapToEntityFromParsingDto(NewsParsingDto dto)
        {
            return new News
            {
                // Id здесь не заполняем, база данных сгенерирует его автоматически (IDENTITY)
                DateTime = dto.DateTime,
                Sport = dto.Sport,
                Title = dto.Title,
                ImageId = dto.ImageId,
                ArticleText = dto.ArticleText,
            };
        }

        public static News MapToEntityFromApiDto(NewsApiDto dto)
        {
            return new News
            {
                Id = dto.Id,
                DateTime = dto.DateTime,
                Sport = dto.Sport,
                Title = dto.Title,
                ImageId = dto.ImageId,
                ArticleText = dto.ArticleText,
            };
        }

        public static NewsApiDto MapToApiDto(News entity)
        {
            return new NewsApiDto
            {
                Id = entity.Id,
                DateTime = entity.DateTime,
                Sport = entity.Sport,
                Title = entity.Title,
                ImageId = entity.ImageId,
                ArticleText = entity.ArticleText,
            };
        }


        public static NewsDetailsDto MapToDtoNewsWithDetails(News entity)
        {
            return new NewsDetailsDto
            {
                Id = entity.Id,
                DateTime = entity.DateTime,
                Sport = entity.Sport,
                Title = entity.Title,
                ImageId = entity.ImageId,
                ArticleText = entity.ArticleText,
            };
        }
    }

}
