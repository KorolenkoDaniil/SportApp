using SportAppServer.Models.Entities;
using System.Diagnostics;

namespace SportAppServer.Models.Mappers
{
    public static class CommentMapper
    {
        public static CommentDTO ConvertToDTO(Comment comment)
        {
            return new CommentDTO(
                comment.CommentId,
                comment.NewsDateTime,
                comment.CommentDateTime,
                comment.CommentText,
                comment.UserEmail
            );
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
                    item.UserEmail
                ));

            };

            foreach (var item in newsCommentsDTOs)
            {
                Debug.WriteLine(item);
            }

            Debug.WriteLine("-------------------------");

            return newsCommentsDTOs;
        }
    }
}
