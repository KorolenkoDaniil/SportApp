using Microsoft.EntityFrameworkCore.Metadata.Internal;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;
using System.Diagnostics;


namespace SportAppServer.Services
{
    public class CommentsService : ICommentsService

    {
        private readonly ICommentsRepository _commentRepository;
        private readonly IUserRepository _userRepository;
    

        public CommentsService(ICommentsRepository newsRepository, IUserRepository userRepository)
        {
            _commentRepository = newsRepository;
            _userRepository = userRepository;
        }

        public async Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, string Viewer, int pageNumber = 1, int pageSize = 10)
        {
            List<CommentDTO> commmentsList = CommentMapper.ConvertToListOfDTO( await _commentRepository.GetPaginatedCommentsList(itemId, pageNumber, pageSize));

            foreach (var item in commmentsList)
            {

                Debug.WriteLine($"{DateTime.UtcNow} {item.CommentDateTime}");

                var elapsedTime = DateTime.UtcNow - item.CommentDateTime.ToUniversalTime();

                if (elapsedTime.TotalDays >= 365)
                {
                    int years = (int)(elapsedTime.TotalDays / 365);
                    item.ElapsedTime = years == 1 ? "1 year ago" : $"{years} years ago";
                }
                else if (elapsedTime.TotalDays >= 30)
                {
                    int months = (int)(elapsedTime.TotalDays / 30);
                    item.ElapsedTime = months == 1 ? "1 month ago" : $"{months} months ago";
                }
                else if (elapsedTime.TotalDays >= 7)
                {
                    int weeks = (int)(elapsedTime.TotalDays / 7);
                    item.ElapsedTime = weeks == 1 ? "1 week ago" : $"{weeks} weeks ago";
                }
                else if (elapsedTime.Days > 0)
                {
                    item.ElapsedTime = elapsedTime.Days == 1 ? "1 day ago" : $"{elapsedTime.Days} days ago";
                }
                else if (elapsedTime.Hours > 0)
                {
                    item.ElapsedTime = elapsedTime.Hours == 1 ? "1 hour ago" : $"{elapsedTime.Hours} hours ago";
                }
                else
                {
                    item.ElapsedTime = elapsedTime.Minutes == 1 ? "1 minute ago" : $"{elapsedTime.Minutes} minutes ago";
                }


                item.LikesCount = await _commentRepository.CountLIkes(item.CommentId);
                item.IsLiked = await _commentRepository.IsLiked(item.CommentId, Viewer);
            }


            int totalItems = await _commentRepository.CountItems(itemId);

            var page = new CommentsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                Comments = commmentsList
            };

            return page;
        }


        public async Task<CommentDTO> PutCommment(CommentDTO comment)
        {
            
            var existingUser = await _userRepository.GetUserData(comment.UserEmail);

            if (existingUser == null)
            {
                existingUser = new User { UserEmail = comment.UserEmail, UserImage = comment.User.UserImage };
                await _userRepository.PutUser(existingUser.UserEmail);
            }

            Comment newComment = CommentMapper.ConvertToEntity(comment);
            newComment.User = existingUser; 

            Comment savedComment = await _commentRepository.PutCommment(newComment);

            CommentDTO returnedComment = CommentMapper.ConvertToDTO(savedComment);

            returnedComment.ElapsedTime = "Just now";

            return returnedComment;
        }


        public async Task<int> AddLike(string LikeAuthor, int CommentId)
        {
           return  await _commentRepository.AddLike(LikeAuthor, CommentId);
        }


        public async Task<int> RemoveLike(string LikeAuthor, int CommentId)
        {
            return await _commentRepository.RemoveLike(LikeAuthor, CommentId);
        }
    }
}
