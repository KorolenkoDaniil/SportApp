using SportAppServer.Models.DTOs;
using SportAppServer.Models.DTOs.Requests;
using SportAppServer.Models.Mappers;
using SportAppServer.Repositories;

namespace SportAppServer.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _newsRepository;

        public UserService(IUserRepository userRepository)
        {
            _newsRepository = userRepository;
        }

        public delegate Task<UserDTO> MyAsyncDelegate(string email);

 
        public async Task<UserDTO> GetUserData(string email)
        {
            return await CheckEmail(email, async (emailToCheck) =>
            {
                return UserMapper.ConvertToDTO(await _newsRepository.GetUserData(emailToCheck));
            });
        }


        public async Task<UserDTO> PutUser(EmailDto email)
        {
            return await CheckEmail(email.Email, async (emailToCheck) => 
            { 
                return UserMapper.ConvertToDTO(await _newsRepository.PutUser(emailToCheck));  
            });
        }
        private async Task<UserDTO> CheckEmail(string email, MyAsyncDelegate methodToExecute)
        {
            if (string.IsNullOrEmpty(email))
            {
                return null;
            }

     
            return await methodToExecute(email);
        }
    }
}
