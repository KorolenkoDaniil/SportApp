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
                .Where(comment =>  comment.NewsDateTime == itemId
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

    }
}
