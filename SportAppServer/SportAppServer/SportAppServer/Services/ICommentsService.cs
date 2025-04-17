using SportAppServer.Models.Entities;
using SportAppServer.Models.Pagination;

namespace SportAppServer.Services
{
    public interface ICommentsService
    {
        Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, int pageNumber = 1, int pageSize = 10);
        Task<CommentDTO> PutCommment(CommentDTO comment);
        Task<int> AddLike(string LikeAuthor, int CommentId);
    }
}
