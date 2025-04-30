using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using System.Diagnostics;

namespace SportAppServer.Models.Mappers
{
    public static class UserMapper
    {
        public static UserDTO ConvertToDTO(User user, bool includeComments = false)
        {
            Debug.WriteLine(user);

            return new UserDTO(
                user.UserEmail,
                user.UserImage,
                includeComments ? CommentMapper.ConvertToListOfDTO(user.Comments) : new List<CommentDTO>()
            );
        }

        public static User ConvertToEntity(UserDTO user)
        {
            return new User(
                user.UserEmail,
                user.UserImage,
                new List<Comment>()
            );
        }


    }
}

