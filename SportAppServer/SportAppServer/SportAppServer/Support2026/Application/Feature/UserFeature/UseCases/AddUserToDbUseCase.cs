using SportAppServer.Support2026.Application.Feature.UserFeature.Dto;
using SportAppServer.Support2026.Application.Feature.UserFeature.Mappers;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.UseCases
{
    public class AddUserToDbUseCase
    {
        private readonly IUserRepository _repository;

        public AddUserToDbUseCase(IUserRepository repository)
        {
            _repository = repository;
        }

        public (UserResponseDto?, AddUserResult) Execute(UserRequestDto dto)
        {
            User entity = UserMapper.MapToEntityFromRequestDto(dto);

            var (user, result) = _repository.AddUserToDB(entity);

            // Ошибка записи или отсутствие объекта
            if (result == AddUserResult.Failed || user == null)
            {
                return (null, AddUserResult.Failed);
            }

            // Для Success или AlreadyExists отдаем DTO и нужный результат
            UserResponseDto responseDto = UserMapper.MapToResponseDto(user);
            return (responseDto, result);
        }

    }
}
