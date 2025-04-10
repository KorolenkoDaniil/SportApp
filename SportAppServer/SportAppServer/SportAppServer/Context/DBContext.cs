using Microsoft.EntityFrameworkCore;
using SportAppServer.Entities.Models;
using SportAppServer.Models.Entities;

namespace SportAppServer.Context
{
    public class DBContext : DbContext
    {
        public DBContext(DbContextOptions<DBContext> options) : base(options) { }

        public DbSet<News> NewsList { get; set; } = null!;
        public DbSet<User> Users { get; set; } = null!;
        public DbSet<NewsTag> Tags { get; set; } = null!;
        public DbSet<Comment> Comments { get; set; } = null!;


        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Server=Karalenka;Database=KorSport;Trusted_Connection=True;TrustServerCertificate=True");
        }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<News>()
                .HasMany(n => n.tags)
                .WithOne(nt => nt.news)
                .HasForeignKey(nt => nt.NewsDateTime);

          
            base.OnModelCreating(modelBuilder);
        }
    }
}





