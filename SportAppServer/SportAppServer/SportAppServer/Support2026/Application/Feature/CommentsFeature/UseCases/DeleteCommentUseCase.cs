using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases
{
    public class DeleteCommentUseCase
    {
        private readonly ICommentRepository _repository;

        public DeleteCommentUseCase(ICommentRepository repository)
        {
            _repository = repository;
        }


        public async Task<bool> Execute(int commentId)
        {
            return await _repository.DeleteComment(commentId);
        }

    }
}
