using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;

namespace SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository
{
    public interface ICommentRepository
    {
        Task<int> CountCommentstoNews(int newsId);
        Task<List<Comment>> GetPaginatedCommentsList(string? email, int newsId, int pageNumber = 1, int pageSize = 10);
        Task<Comment?> AddNewComment(Comment comment);
        Task<bool> DeleteComment(int commentId);
        Task<bool> EditComment(int commentId, string newText);
    }
}
