using Microsoft.EntityFrameworkCore;
using SportAppServer.Entities.Models;
using SportAppServer.Models.Entities;

namespace SportAppServer.Context
{
    public class DBContext : DbContext
    {
        public DBContext() { }

        public DBContext(DbContextOptions<DBContext> options) : base(options) { }

        public DbSet<News> NewsList { get; set; } = null!;
        public DbSet<User> Users { get; set; } = null!;
        public DbSet<NewsTag> Tags { get; set; } = null!;
        public DbSet<Comment> Comments { get; set; } = null!;
        public DbSet<Like> Likes { get; set; } = null!;
        public DbSet<CommentLike> Likes { get; set; } = null!;


        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Server=Karalenka;Database=KorSport;Trusted_Connection=True;TrustServerCertificate=True");
        }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<News>()
                .HasMany(n => n.Tags)
                .WithOne(nt => nt.News)
                .HasForeignKey(nt => nt.NewsDateTime);

            modelBuilder.Entity<Comment>()
               .HasOne(c => c.User)
               .WithMany(user => user.Comments)
               .HasForeignKey(c => c.UserEmail);

            //modelBuilder.Entity<Like>()
            //  .HasOne(c => c.User)
            //  .WithMany(user => user.Likes)
            //  .HasForeignKey(c => c.UserEmail);


            base.OnModelCreating(modelBuilder);
        }
    }
}





