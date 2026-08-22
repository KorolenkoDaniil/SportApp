using Microsoft.IdentityModel.Tokens;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.UseCases
{
    public class CheckUserExistsUseCase
    {
        private readonly IUserRepository _userRepository;

        public CheckUserExistsUseCase (IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }

        public async Task<bool> Execute(string email)
        {
            if (email.IsNullOrEmpty())
            {
                Console.WriteLine("GetUserByEmailUseCase Execute пустой email");
                return false;
            }

            return  await _userRepository.CheckUserExists(email);
        }
    }
}
