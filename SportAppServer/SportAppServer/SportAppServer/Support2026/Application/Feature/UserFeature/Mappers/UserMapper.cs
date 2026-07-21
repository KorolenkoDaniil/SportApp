using SportAppServer.Support2026.Application.Feature.UserFeature.Dto;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.Mappers
{
    public static class UserMapper
    {
        public static User MapToEntityFromRequestDto(UserRequestDto dto) => new User
        {
            UserName = dto.UserName,
            Email = dto.Email,
        };

        public static UserResponseDto MapToResponseDto(User entity)
        {
            return new UserResponseDto
            {
                Id = entity.Id,
                UserName = entity.UserName,
                Email = entity.Email,
                ImageId = entity.ImageId,
            };
        }
    }
}
