using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface ILikeRepository
    {
        Task<int> AddLikeAsync(Like like);
        Task<int> RemoveLikeAsync(DateTime newsDateTime, string userEmail);
        Task<bool> LikeExist(DateTime newsDateTime, string userEmail);
    }
}
