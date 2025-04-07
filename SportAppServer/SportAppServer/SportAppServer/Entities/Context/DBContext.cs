using Microsoft.EntityFrameworkCore;
using SportAppServer.Entities.models;
using SportAppServer.Entities.Models;

namespace SportAppServer.Entities.context
{
    public class DBContext : DbContext
    {

        public DBContext(DbContextOptions<DBContext> options) : base(options) { }

        public DBContext() { }
        public DbSet<News> News { get; set; } = null!;
        public DbSet<User> Users { get; set; } = null!;
        public DbSet<NewsTag> Tags { get; set; } = null!;
        public DbSet<NewsComments> NewsComments { get; set; } = null!;


        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Server=Karalenka;Database=KorSport;Trusted_Connection=True;TrustServerCertificate=True");
        }



      

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<News>()
                .HasMany(n => n.NewsTags)
                .WithOne(nt => nt.News)
                .HasForeignKey(nt => nt.NewsDateTime);

            modelBuilder.Entity<News>()
                .HasMany(n => n.NewsComments)
                .WithOne(nc => nc.News)
                .HasForeignKey(nc => nc.NewsDateTime);

            base.OnModelCreating(modelBuilder);
        }





        public async Task AddNewsToDBAsync(List<News> newsList)
        {
            try
            {
                foreach (var newsItem in newsList)
                {
                    var newsFound = await News
                    .Include(n => n.NewsTags)
                    .FirstOrDefaultAsync(n => n.DateTime == newsItem.DateTime);

                    if (newsFound == null)
                    {
                        if (newsItem.Title != null)
                        {
                            
                            var tags = await Gemini.CreateTags(newsItem.ArticleText);
                            List<NewsTag> emptyTags = new List<NewsTag>();

                            foreach (var item in tags)
                            {
                                var newTag = new NewsTag(item, newsItem.DateTime);
                                await Tags.AddAsync(newTag);
                                emptyTags.Add(newTag);
                            }

                            newsItem.NewsTags = emptyTags;

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





