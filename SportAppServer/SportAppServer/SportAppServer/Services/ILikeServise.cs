using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface ILikeServise
    {
        Task<int> AddLikeAsync(LikeDto like);
        Task<int> RemoveLikeAsync(LikeDto like); 
    }
}
