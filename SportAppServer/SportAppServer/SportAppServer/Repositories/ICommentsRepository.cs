using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface ICommentsRepository
    {
        Task<List<Comment>> GetPaginatedCommentsList(DateTime itemId, int pageNumber = 1, int pageSize = 10);
        Task<int> CountItems(DateTime itemId);
    }
}
