using SportAppServer.Models.Entities;
using System.Diagnostics;

namespace SportAppServer.Models.Mappers
{
    public static class CommentMapper
    {
        public static CommentDTO ConvertToDTO(Comment comment)
        {
            return new CommentDTO
            {
                CommentId = comment.CommentId,
                CommentText = comment.CommentText,
                CommentDateTime = comment.CommentDateTime,
                NewsDateTime = comment.NewsDateTime,
                UserEmail = comment.UserEmail,
                User = UserMapper.ConvertToDTO(comment.User)
            };
        }


        public static Comment ConvertToEntity(CommentDTO comment)
        {
            return new Comment
            {
                CommentId = comment.CommentId,
                CommentText = comment.CommentText,
                CommentDateTime = comment.CommentDateTime,
                NewsDateTime = comment.NewsDateTime,
                UserEmail = comment.UserEmail,
                User = UserMapper.ConvertToEntity(comment.User)
            };
        }


        public static List<CommentDTO> ConvertToListOfDTO(List<Comment> comments)
        {
            List<CommentDTO> newsCommentsDTOs = new List<CommentDTO>();

            foreach (var item in comments)
            {
                newsCommentsDTOs.Add(new CommentDTO(
                    item.CommentId,
                    item.NewsDateTime,
                    item.CommentDateTime,
                    item.CommentText,
                    item.UserEmail,
                    item.User != null ? UserMapper.ConvertToDTO(item.User) : null 
                ));
            }

            foreach (var item in newsCommentsDTOs)
            {
                Debug.WriteLine(item);
            }

            Debug.WriteLine("-------------------------");

            return newsCommentsDTOs;
        }
    }
}

