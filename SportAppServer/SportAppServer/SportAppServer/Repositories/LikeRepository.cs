using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using SportAppServer.Context;
using SportAppServer.Models.Entities;
using System.Data;
using System.Diagnostics;

namespace SportAppServer.Repositories
{
    public class LikeRepository: ILikeRepository
    {
        private readonly DBContext _context;
        public LikeRepository(DBContext context)
        {
            _context = context;
        }
        public async Task<int> AddLikeAsync(Like like)
        {

            Like? likeExist = await _context.Likes.FirstOrDefaultAsync(
                l => l.NewsDateTime == like.NewsDateTime && l.UserEmail == like.UserEmail);

            if (likeExist == null)
            {
                await _context.Likes.AddAsync(like);
                await _context.SaveChangesAsync();

                return await CountLikes(like.NewsDateTime);
            }
            else return -1;
        }

        public async Task<bool> LikeExist(DateTime newsDateTime, string userEmail)
        {
            Like? likeExist = await _context.Likes.FirstOrDefaultAsync(
                l => l.NewsDateTime == newsDateTime && l.UserEmail == userEmail);

            if (likeExist == null) return false;
            else return true;

        }



        public async Task<int> RemoveLikeAsync(DateTime newsDateTime, string userEmail)
        {
            var like = await _context.Likes
                .FirstOrDefaultAsync(x => x.NewsDateTime == newsDateTime && x.UserEmail == userEmail);

            if (like != null)
            {
                _context.Likes.Remove(like);
                await _context.SaveChangesAsync();

                return await CountLikes(newsDateTime);
            }

            else return -1;
        }


        private async Task<int> CountLikes(DateTime newsDateTime)
        {
            Debug.WriteLine($"CountLikes: {newsDateTime}");

            var param = new SqlParameter("@newsDateTime", newsDateTime);
            var outputParam = new SqlParameter("@OutputCount", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };


            await _context.Database.ExecuteSqlRawAsync(
                "EXEC [dbo].[LikesCount] @newsDateTime, @OutputCount OUT",
                param, outputParam
            );

            var result = (int)outputParam.Value;

            Debug.WriteLine($"CountLikes: {result}");

            return result;
        }
    }
}
