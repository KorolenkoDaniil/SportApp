using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;

namespace SportAppServer.Support2026.Domain.Feature.UserFeature.Repository
{
    public class UserRepository : IUserRepository
    {
        public Task AddUserToDBAsync(User user)
        {
            throw new NotImplementedException();
        }

        public Task<User?> GetByiDAsync(int userId)
        {
            throw new NotImplementedException();
        }
    }
}
