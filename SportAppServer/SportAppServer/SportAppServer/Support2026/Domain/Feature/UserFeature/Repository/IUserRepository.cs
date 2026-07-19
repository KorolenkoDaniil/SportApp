using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;

namespace SportAppServer.Support2026.Domain.Feature.UserFeature.Repository
{
    public interface IUserRepository
    {
        Task<User?> GetByiDAsync(int userId);
        Task AddUserToDBAsync(User user);
    }
}
