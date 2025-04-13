using SportAppServer.Context;
using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly DBContext _context;

        public UserRepository (DBContext context)
        {
            _context = context;
        }

        public async Task<User> GetUserData(string email)
        {
            User user = await _context.Users.FindAsync(email);

            return user;
        }

        public async Task<User> PutUser(string email)
        {
            List<Comment> comments = new List<Comment>();

            var newUser = new User(email, "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg", comments);

            _context.Users.Add(newUser);

            await _context.SaveChangesAsync();

            return newUser;
        }

    }
}


