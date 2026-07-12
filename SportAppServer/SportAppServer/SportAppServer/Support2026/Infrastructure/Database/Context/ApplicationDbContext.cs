using Microsoft.EntityFrameworkCore;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;

namespace SportAppServer.Support2026.Infrastructure.Database.Context
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) 
            : base(options) 
        {
            Database.EnsureCreated();
        }

        public DbSet<News> NewsList { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //optionsBuilder.LogTo(Console.WriteLine);
            ////optionsBuilder.UseSqlServer(
            //optionsBuilder.
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfigurationsFromAssembly(typeof(ApplicationDbContext).Assembly);
            // - автоматическая сборка всех классов, реализующих IEntityTypeConfiguration

            //modelBuilder.ApplyConfiguration(new NewsConfiguration());
        }
    }
}





