using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases
{
    public class EditCommentUseCase
    {
        private readonly ICommentRepository _repository;

        public EditCommentUseCase(ICommentRepository repository)
        {
            _repository = repository;
        }

        public async Task<bool> Execute(int commentId, string newText)
        {
            if (commentId <= 0 || string.IsNullOrWhiteSpace(newText))
                return false;

            return await _repository.EditComment(commentId, newText.Trim());
        }
    }
}