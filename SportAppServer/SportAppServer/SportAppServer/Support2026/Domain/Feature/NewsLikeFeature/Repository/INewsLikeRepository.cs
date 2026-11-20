using SportAppServer.Support2026.Application.Feature.NewsLikeFeature.Dto;

namespace SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository
{
    public interface INewsLikeRepository
    {

        public Task UpdateNewsLikeStatus (NewsLikeDto newsLike);
        public Task<bool> CheckIfUserLikedNews(int newsId, string userEmail);
        public Task<int> CountNewsLikes(int newsId);
    }
}
