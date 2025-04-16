using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;


namespace SportAppServer.Services
{
    public class CommentsService : ICommentsService

    {
        private readonly ICommentsRepository _commentRepository;
        private readonly IUserRepository _userRepository;

        public CommentsService(ICommentsRepository newsRepository, IUserRepository userRepository)
        {
            _commentRepository = newsRepository;
            _userRepository = userRepository;
        }

        public async Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, int pageNumber = 1, int pageSize = 10)
        {
            List<Comment> commmentsList = await _commentRepository.GetPaginatedCommentsList(itemId, pageNumber, pageSize);

            int totalItems = await _commentRepository.CountItems(itemId);

            var page = new CommentsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                Comments = CommentMapper.ConvertToListOfDTO(commmentsList)
            };

            return page;
        }


        public async Task<CommentDTO> PutCommment(CommentDTO comment)
        {
            
            var existingUser = await _userRepository.GetUserData(comment.UserEmail);

            if (existingUser == null)
            {
                existingUser = new User { UserEmail = comment.UserEmail, UserImage = comment.User.UserImage };
                await _userRepository.PutUser(existingUser.UserEmail);
            }

            Comment newComment = CommentMapper.ConvertToEntity(comment);
            newComment.User = existingUser; 

            Comment savedComment = await _commentRepository.PutCommment(newComment);

            CommentDTO returnedComment = CommentMapper.ConvertToDTO(savedComment);

            return returnedComment;
        }

    }
}
