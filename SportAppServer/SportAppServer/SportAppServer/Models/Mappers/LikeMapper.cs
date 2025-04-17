using SportAppServer.Models.Entities;

namespace SportAppServer.Models.Mappers
{
    public static class LikeMapper
    {
        public static LikeDto ConvertToDTO(Like like)
        {
            return new LikeDto
            {
                UserEmail = like.UserEmail,
                NewsDateTime = like.NewsDateTime
            };
        }

        public static Like ConvertToEntity(LikeDto like)
        {
            return new Like
            {
                NewsDateTime = like.NewsDateTime,
                UserEmail = like.UserEmail
            };
        }
    }
}

