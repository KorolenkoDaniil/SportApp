using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;

namespace SportAppServer.Models.Mappers
{
    public static class UserMapper
    {
        public static UserDTO ConvertToDTO(User user)
        {
            return new UserDTO(
                user.UserEmail,
                user.UserImage
            );
        }
    }
}
