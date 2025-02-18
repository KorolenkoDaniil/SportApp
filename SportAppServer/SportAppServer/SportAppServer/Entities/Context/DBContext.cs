using Microsoft.EntityFrameworkCore;
using SportAppServer.Entities.models;
using SportAppServer.Entities.Models;

namespace SportAppServer.Entities.context
{
    public class DBContext : DbContext
    {

        public DBContext(DbContextOptions<DBContext> options) : base(options) { }

        public DBContext()
        {
        }

        public DbSet<News> News { get; set; } = null!;

        public DbSet<User> Users { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Server=Karalenka;Database=SportAppDB;Trusted_Connection=True;TrustServerCertificate=True");
        }

        public async Task AddNewsToDBAsync(List<News> newsList)
        {
            try
            {
                foreach (var newsItem in newsList)
                {
                    var newsFound = await News.FindAsync(newsItem.DateTime);
                    if (newsFound == null)
                    {
                        if (newsItem.Title != null)
                        {
                            await News.AddAsync(newsItem);
                            Console.WriteLine($"Новость добавлена: {newsItem.Title}");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"Новость уже существует: {newsItem.Title}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при добавлении новостей: {ex.Message}");
            }
        }

        internal object FindAsync(string email)
        {
            throw new NotImplementedException();
        }
    }
}
