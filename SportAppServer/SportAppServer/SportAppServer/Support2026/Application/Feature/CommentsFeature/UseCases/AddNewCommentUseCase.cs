using SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases
{
    public class AddNewCommentUseCase
    {
        private readonly ICommentRepository _repository;

        public AddNewCommentUseCase (ICommentRepository repository)
        {
            _repository = repository;
        }


        //todo сделать проверку есть ли такой email и новость
        public async Task<Comment?> AddNewsComment(CommentDtoRequest commentDtoRequest)
        {
            if (commentDtoRequest.NewsId < 1 ||
                string.IsNullOrWhiteSpace(commentDtoRequest.Email) ||
                string.IsNullOrWhiteSpace(commentDtoRequest.CommentText))
            {
                return null;
            }

            var comment = new Comment(commentDtoRequest.CommentText, commentDtoRequest.NewsId, commentDtoRequest.Email);
            
            return await _repository.AddNewComment(comment);
        }
    }
}
