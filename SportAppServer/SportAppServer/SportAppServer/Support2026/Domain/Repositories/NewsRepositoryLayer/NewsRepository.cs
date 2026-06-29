using Microsoft.EntityFrameworkCore;
using SportAppServer.Support2026.Domain.Entities;
using SportAppServer.Support2026.Infrastructure.Database.Context;

namespace SportAppServer.Support2026.Domain.Repositories.NewsRepositoryLayer
{
    public class NewsRepository : INewsRepository
    {

        private readonly ApplicationDbContext _context;

        public NewsRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task AddNewsToDbAsync(List<News> newsList)
        {
            try
            {
                foreach (var newsItem in newsList)
                {
                    var newsFound = await _context.NewsList.FirstOrDefaultAsync(n => n.DateTime == newsItem.DateTime);

                    if (newsFound == null)
                    {
                        await _context.NewsList.AddAsync(newsItem);
                        Console.WriteLine($"Новость добавлена: {newsItem.Title}");
                    }
                    else
                        Console.WriteLine($"Новость уже существует: {newsItem.Title}");
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при добавлении новостей: {ex.Message}");
            }
        }

        public Task AddNewsToDBAsync(List<News> newsList)
        {
            throw new NotImplementedException();
        }

        public Task<int> CountItems()
        {
            return _context.NewsList.CountAsync();
        }

        public Task<List<News>> GetAllNews()
        {
            throw new NotImplementedException();
        }

        public async Task<News?> GetByDateAsync(DateTime dateTime)
        {
            return await _context.NewsList.FirstOrDefaultAsync(n => n.DateTime == dateTime);    
        }

        public async Task<List<News>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10)
        {
            return await _context.NewsList.OrderByDescending(n => n.DateTime)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();
        }



    }
}
