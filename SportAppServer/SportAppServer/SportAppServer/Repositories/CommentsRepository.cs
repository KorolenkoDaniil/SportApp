using Microsoft.EntityFrameworkCore;
using SportAppServer.Context;
using SportAppServer.Models.Entities;
using System.Diagnostics;

namespace SportAppServer.Repositories
{
    public class CommentsRepository : ICommentsRepository
    {
        private readonly DBContext _context;

        public CommentsRepository(DBContext context)
        {
            _context = context;
        }

        public async Task<int> CountItems(DateTime itemId)
        {
            var normalized = new DateTime(itemId.Year, itemId.Month, itemId.Day, itemId.Hour, itemId.Minute, itemId.Second);
            var nextSecond = normalized.AddSeconds(1);

            return await _context.Comments
                .Where(comment => comment.NewsDateTime >= normalized && comment.NewsDateTime < nextSecond)
                .CountAsync();
        }


        public async Task<List<Comment>> GetPaginatedCommentsList(DateTime itemId, int pageNumber, int pageSize)
        {

            Debug.WriteLine(itemId.ToString());


            var commentsList = await _context.Comments
                .Where(comment => comment.NewsDateTime == itemId
                )
                .OrderByDescending(comment => comment.CommentDateTime)
                .Skip((pageNumber - 1) * pageSize)
                .Include(comment => comment.User)
                .Take(pageSize)
                .ToListAsync();

            foreach (var item in commentsList)
            {
                Debug.WriteLine(commentsList);
            }


            return commentsList;
        }

        public async Task<Comment> PutCommment(Comment comment)
        {
            await _context.Comments.AddAsync(comment);
            await _context.SaveChangesAsync();
            return comment;
        }


        public async Task<int> AddLike(string LikeAuthor, int CommentId)
        {
            var comment = await _context.Comments.FirstOrDefaultAsync(c => c.CommentId == CommentId);

            bool alreadyLiked = await _context.CommentsLikes
                .AnyAsync(cl => cl.CommentId == CommentId && cl.LikedByUserEmail == LikeAuthor);

            if (comment == null || alreadyLiked)
                return -1;

            var like = new CommentLike(CommentId, LikeAuthor, comment, comment.User);
            await _context.CommentsLikes.AddAsync(like);

            comment.LikesCount++;

            await _context.SaveChangesAsync();

            // Сразу проверим, что реально видим 3 лайка
            var count = await _context.CommentsLikes
                .CountAsync(cl => cl.CommentId == CommentId);

            Console.WriteLine($"Сейчас лайков к комменту {CommentId}: {count}");

            return count;
        }

    }
}
