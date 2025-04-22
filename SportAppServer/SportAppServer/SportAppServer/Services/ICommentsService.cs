using Microsoft.EntityFrameworkCore.Metadata.Internal;
using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Pagination;

namespace SportAppServer.Services
{
    public interface ICommentsService
    {
        Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, string Viewer, int pageNumber = 1, int pageSize = 10);
        //Task<CommentsPagination> GetPaginatedNewsListWithSearch(DateTime itemId, string Viewer, string searchPrompt, int pageNumber = 1, int pageSize = 10);
        Task<CommentDTO> PutCommment(CommentDTO comment);
        Task<int> AddLike(string LikeAuthor, int CommentId);
        Task<int> RemoveLike(string LikeAuthor, int CommentId);
    }
}
