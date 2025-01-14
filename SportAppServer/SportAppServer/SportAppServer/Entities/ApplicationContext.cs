using Microsoft.EntityFrameworkCore;

namespace SportAppServer.Entities
{
    public class ApplicationContext : DbContext
    {
        public DbSet<News> News { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Server=Karalenka;Database=SportAppDB;Trusted_Connection=True;TrustServerCertificate=True");
        }
    }
}
