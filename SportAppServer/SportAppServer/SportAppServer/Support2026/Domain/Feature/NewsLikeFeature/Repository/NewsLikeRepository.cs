using SportAppServer.Support2026.Application.Feature.NewsLikeFeature.Dto;
using SportAppServer.Support2026.Infrastructure.Database.Context;

namespace SportAppServer.Support2026.Domain.Feature.LikeFeature.Repository
{
    public class NewsLikeRepository : INewsLikeRepository
    {
        private readonly ApplicationDbContext _context;

        public NewsLikeRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public ApplicationDbContext Get_context()
        {
            return _context;
        }

        public async Task<bool> CheckIfUserLikedNews(int newsId, string userEmail)
        {
            return await Task.FromResult(_context.NewsLikes
                .Any(like => like.NewsId == newsId && like.UserEmail == userEmail));
        }

        public async Task UpdateNewsLikeStatus(NewsLikeDto newsLikeDto)
        {
            NewsLike? existingLike = _context.NewsLikes
                .FirstOrDefault(like => like.NewsId == newsLikeDto.NewsId && like.UserEmail == newsLikeDto.UserEmail);

            if (existingLike != null)
            {
                _context.NewsLikes.Remove(existingLike);
            }
            else
            {
                NewsLike newLike = new NewsLike
                {
                    NewsId = newsLikeDto.NewsId,
                    UserEmail = newsLikeDto.UserEmail
                };
                _context.NewsLikes.Add(newLike);
            }

            _context.SaveChanges();
        }
    }
}