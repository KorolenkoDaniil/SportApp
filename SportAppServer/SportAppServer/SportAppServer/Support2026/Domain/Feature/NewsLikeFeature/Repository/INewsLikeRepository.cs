using SportAppServer.Support2026.Application.Feature.NewsLikeFeature.Dto;

namespace SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository
{
    public interface INewsLikeRepository
    {

        public void UpdateNewsLikeStatus (NewsLikeDto newsLike);
        public bool CheckIfUserLikedNews(int newsId, string userEmail);
    }
}
