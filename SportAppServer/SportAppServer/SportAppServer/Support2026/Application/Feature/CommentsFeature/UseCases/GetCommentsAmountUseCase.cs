using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases
{
    public class GetCommentsAmountUseCase
    {
        private readonly ICommentRepository _repository;
       
        public GetCommentsAmountUseCase(ICommentRepository repository)
        {
            _repository = repository;
        }

        public async Task<int> Execute(int newsId)
        {
            if (newsId < 1)
            {
                Console.WriteLine("GetCommentsAmountUseCase не верное ID новости" + newsId);
                return 0;
            }

            return await _repository.CountCommentstoNews(newsId);
        }
    }
}
