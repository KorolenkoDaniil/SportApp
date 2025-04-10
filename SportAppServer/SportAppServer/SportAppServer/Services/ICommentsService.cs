using SportAppServer.Models.Pagination;
using System.Threading.Tasks;

namespace SportAppServer.Services
{
    public interface ICommentsService
    {
        Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, int pageNumber = 1, int pageSize = 10);
    }
}
