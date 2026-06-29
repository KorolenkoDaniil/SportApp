using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Domain.Entities;

namespace SportAppServer.Support2026.Application.Mappers
{
    public static class NewsMapper
    {
        public static News MapToEntity (NewsDtoFromParser dto)
        {
            return new News
            {
                DateTime = dto.DateTime,
                Sport = dto.Sport,
                Title = dto.Title,
                ImageId = dto.ImageId,
                ArticleText = dto.ArticleTexts,
            };
        }

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
