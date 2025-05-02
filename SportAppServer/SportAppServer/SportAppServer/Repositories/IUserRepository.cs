using SportAppServer.Models.DTOs.Requests;
using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface IUserRepository
    {
        Task<User> PutUser(string email);
        Task<User> GetUserData(string email);
        Task<string> PutUserImage(string email, IFormFile image);
    }
}
