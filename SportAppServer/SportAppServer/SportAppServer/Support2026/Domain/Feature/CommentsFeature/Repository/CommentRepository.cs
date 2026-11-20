using Microsoft.EntityFrameworkCore;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;
using SportAppServer.Support2026.Infrastructure.Database.Context;

namespace SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository
{
    public class CommentRepository : ICommentRepository
    {

        private readonly ApplicationDbContext _context;

        public CommentRepository(ApplicationDbContext context)
        {
            _context = context;
        }



        public async Task<Comment?> AddNewComment(Comment comment)
        {
            try
            {
                await _context.Comments.AddAsync(comment);
                await _context.SaveChangesAsync();

                return comment ;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при добавлении Комментария: {ex.Message}");
                return null;
            }
        }

        public async Task<int> CountCommentstoNews(int newsId)
        {
            return await _context.Comments.Where(com => com.NewsId == newsId).CountAsync();
        }

        public async Task<bool> DeleteComment(int commentId)
        {
            int deletedCount = await _context.Comments
                .Where(com => com.CommentId == commentId)
                .ExecuteDeleteAsync();

            return deletedCount > 0;
        }

        public async Task<bool> EditComment(int commentId, string newText)
        {
            int editedCount = await _context.Comments
                .Where(com => com.CommentId == commentId)
                .ExecuteUpdateAsync(setters => setters
                    .SetProperty(com => com.CommentText, newText));

            return editedCount > 0;
        }

        public async Task<List<Comment>> GetPaginatedCommentsList(string? email, int newsId, int pageNumber = 1, int pageSize = 10)
        {


       
            List<Comment> newsComments = await _context.Comments
                .AsNoTracking()
                .OrderByDescending(n => n.CommentDateTime)
                .Where(com => com.NewsId == newsId)
                .Skip((pageNumber -1) * pageSize)
                .Take(pageSize)
                .ToListAsync();

            if (string.IsNullOrWhiteSpace(email))
                return newsComments;

            List<Comment> userComments = await _context.Comments
                .AsNoTracking()
                .OrderByDescending(n => n.CommentDateTime)
                .Where(com => com.Email == email && com.NewsId == newsId)
                .Take(3)
                .ToListAsync();

            return userComments.Concat(newsComments)
                .DistinctBy(com => com.CommentId)
                .ToList();
        }
    }
}
