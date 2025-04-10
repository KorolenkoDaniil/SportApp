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
            return await checkEmail(email, async (emailToCheck) =>
            {
                return UserMapper.ConvertToDTO(await _newsRepository.GetUserData(emailToCheck));
            });
        }


        public async Task<UserDTO> PutUser(EmailDto email)
        {
            return await checkEmail(email.Email, async (emailToCheck) => 
            { 
                return UserMapper.ConvertToDTO(await _newsRepository.PutUser(emailToCheck));  
            });
        }
        private async Task<UserDTO> checkEmail(string email, MyAsyncDelegate methodToExecute)
        {
            if (string.IsNullOrEmpty(email))
            {
                return null;
            }

     
            return await methodToExecute(email);
        }
    }
}
