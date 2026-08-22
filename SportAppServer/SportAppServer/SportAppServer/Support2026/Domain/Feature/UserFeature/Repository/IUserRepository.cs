using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;

namespace SportAppServer.Support2026.Domain.Feature.UserFeature.Repository
{
    public interface IUserRepository
    {
        User? GetByiD(int userId);
        public Task<User?> GetByEmail(string email);
        (User?, AddUserResult) AddUserToDB(User user);
        public Task<bool> CheckUserExists(string email);
    }
}
