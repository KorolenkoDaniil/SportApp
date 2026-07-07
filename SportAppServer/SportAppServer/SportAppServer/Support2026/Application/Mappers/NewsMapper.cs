using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Domain.Entities;

namespace SportAppServer.Support2026.Application.Mappers
{
    public static class NewsMapper
    {

        /// <summary> Mapping of News from dto to entity 
        public static News MapToEntity (NewsDto dto)
        {
            return new News
            {
                DateTime = dto.DateTime,
                Sport = dto.Sport,
                Title = dto.Title,
                ImageId = dto.ImageId,
                ArticleText = dto.ArticleText,
            };
        }


        /// <summary> Mapping of News from entity to dto 
        public static NewsDto MapToDto(News entity)
        {
            return new NewsDto
            {
                DateTime = entity.DateTime,
                Sport = entity.Sport,
                Title = entity.Title,
                ImageId = entity.ImageId,
                ArticleText = entity.ArticleText,
            };
        }
    }

}
