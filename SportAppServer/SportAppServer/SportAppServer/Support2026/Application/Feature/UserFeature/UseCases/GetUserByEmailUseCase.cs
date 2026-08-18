using Microsoft.IdentityModel.Tokens;
using SportAppServer.Support2026.Application.Feature.UserFeature.Dto;
using SportAppServer.Support2026.Application.Feature.UserFeature.Mappers;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.UseCases
{
    public class GetUserByEmailUseCase
    {
        private readonly IUserRepository _userRepository;

        public GetUserByEmailUseCase(IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }

        public async Task<UserResponseDto?> Execute(string email)
        {
            if (email.IsNullOrEmpty())
            {
                Console.WriteLine("GetUserByEmailUseCase Execute пустой email");
                return null;
            }

            User? user = await _userRepository.GetByEmail(email);

            if (user != null)
            {
                UserResponseDto userDto = UserMapper.MapToResponseDto(user);

                return userDto;
            }
            else
            {
                Console.WriteLine("GetUserByEmailUseCase: пользователь не найден  " + email);
                return null;
            }
        }
    }
}
