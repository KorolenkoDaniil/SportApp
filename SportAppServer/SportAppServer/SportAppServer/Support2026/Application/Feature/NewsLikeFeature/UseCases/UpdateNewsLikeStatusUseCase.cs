using SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.NewsLikeFeature.UseCases
{
    public class UpdateNewsLikeStatusUseCase
    {
        private readonly INewsLikeRepository _newsLikeRepository;

        public UpdateNewsLikeStatusUseCase(INewsLikeRepository newsLikeRepository)
        {
            _newsLikeRepository = newsLikeRepository;
        }

        public async Task Execute(int newsId, string userEmail)
        {

            await _newsLikeRepository.UpdateNewsLikeStatus(new Dto.NewsLikeDto
            {
                NewsId = newsId,
                UserEmail = userEmail
            });
        }
    }
}
