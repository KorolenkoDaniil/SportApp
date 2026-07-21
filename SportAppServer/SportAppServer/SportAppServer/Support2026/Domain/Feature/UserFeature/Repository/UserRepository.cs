using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;
using SportAppServer.Support2026.Infrastructure.Database.Context;

namespace SportAppServer.Support2026.Domain.Feature.UserFeature.Repository
{
    public enum AddUserResult
    {
        Success,
        AlreadyExists,
        Failed
    }


    public class UserRepository : IUserRepository
    {
        private readonly ApplicationDbContext _context; 
        public UserRepository(ApplicationDbContext context) { 
            _context = context;
        }

        public ApplicationDbContext Get_context()
        {
            return _context;
        }

        public (User?, AddUserResult) AddUserToDB(User user)
        {
            try
            {
                User? existingUser = _context.Users.FirstOrDefault(u => u.Email == user.Email);

                if (existingUser != null)
                {
                    return (existingUser, AddUserResult.AlreadyExists);
                }

                _context.Users.Add(user);
                _context.SaveChanges();

                return (user, AddUserResult.Success);
            }
            catch (Exception)
            {
                return (null, AddUserResult.Failed);
            }
        }

        public User? GetByiD(int userId)
        {
            User? existingUser = _context.Users.FirstOrDefault(u => u.Id == userId);
            return existingUser;
        }

        public User? GetByEmail(string email)
        {
            User? existingUser = _context.Users.FirstOrDefault(u => u.Email == email);
            return existingUser;
        }
    }
}
