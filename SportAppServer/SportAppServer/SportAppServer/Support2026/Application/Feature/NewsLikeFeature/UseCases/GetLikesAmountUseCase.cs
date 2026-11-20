using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;
using SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.NewsLikeFeature.UseCases
{
    public class GetLikesAmountUseCase
    {
        private readonly INewsLikeRepository _repository;


        public GetLikesAmountUseCase(INewsLikeRepository repository)
        {
            _repository = repository;
        }


        public async Task<int> Execute(int newsId)
        {
            if (newsId < 1)
            {
                Console.WriteLine("GetLikesAmountUseCase не верное ID новости" + newsId);
                return 0;
            }

            return await _repository.CountNewsLikes(newsId);
        }
    }
}