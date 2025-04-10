using SportAppServer.Models.DTOs;
using SportAppServer.Models.DTOs.Requests;

namespace SportAppServer.Services
{
    public interface IUserService
    {
        Task<UserDTO> PutUser(EmailDto email);
        Task<UserDTO> GetUserData(string email);
    }
}
