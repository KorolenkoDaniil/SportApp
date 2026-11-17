using SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.NewsLikeFeature.UseCases
{
    public class CheckIfUserLikedNewsUseCase
    {
        private readonly INewsLikeRepository _newsLikeRepository;
        public CheckIfUserLikedNewsUseCase(INewsLikeRepository newsLikeRepository)
        {
            _newsLikeRepository = newsLikeRepository;
        }
        public async Task<bool> ExecuteAsync(int newsId, string userEmail)
        {
            var like = await _newsLikeRepository.CheckIfUserLikedNews(newsId, userEmail);
            return like != null;
        }
    }
}
