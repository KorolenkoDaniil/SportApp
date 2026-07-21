//using SportAppServer.Models.DTOs;
//using SportAppServer.Models.DTOs.Requests;
//using SportAppServer.Models.Mappers;
//using SportAppServer.Repositories;
//using System.Diagnostics;

//namespace SportAppServer.Services
//{
//    public class UserService : IUserService
//    {
//        private readonly IUserRepository _userRepository;

//        public UserService(IUserRepository userRepository)
//        {
//            _userRepository = userRepository;
//        }

//        public async Task<UserDTO> GetUserData(string email)
//        {
//            if (string.IsNullOrWhiteSpace(email))
//                return null;

//            Debug.WriteLine("-> Вызван метод GetUserData");
//            Debug.WriteLine(email);

//            var userEntity = await _userRepository.GetUserData(email);
//            return UserMapper.ConvertToDTO(userEntity);
//        }

//        public async Task<UserDTO> AddUser(EmailDto email)
//        {
//            if (string.IsNullOrWhiteSpace(email?.Email))
//                return null;

//            var userEntity = await _userRepository.AddUser(email.Email);
//            return UserMapper.ConvertToDTO(userEntity);
//        }

//        public async Task<string> PutUserImage(string email, IFormFile image)
//        {
//            return await _userRepository.PutUserImage(email, image);
//        }


//        public async Task<UserDTO> ChangeTheme(string email)
//        {
//            if (string.IsNullOrWhiteSpace(email))
//                return null;

//            Debug.WriteLine("-> Вызван метод ChangeTheme");
//            Debug.WriteLine(email);

//            var userEntity = await _userRepository.ChangeTheme(email);

//            return UserMapper.ConvertToDTO(userEntity);
//        }
     
//    }
//}
