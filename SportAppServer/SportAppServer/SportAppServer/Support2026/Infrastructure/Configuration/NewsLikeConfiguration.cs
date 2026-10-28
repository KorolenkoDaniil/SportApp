using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using SportAppServer.Support2026.Domain.Feature.LikeFeature;



namespace SportAppServer.Support2026.Infrastructure.Configuration
{
    public class NewsLikeConfiguration : IEntityTypeConfiguration<NewsLike>
    {
        public void Configure(EntityTypeBuilder<NewsLike> builder)
        {

            builder.ToTable("NewsLike");

            builder.HasKey(n => n.LikeId);
            builder.Property<int>("LikeId")
                .HasColumnName("LikeId")
                .ValueGeneratedOnAdd();

            builder.Property(n => n.NewsId)
                 .HasColumnName("NewsId")
                 .IsRequired();

            builder.Property(n => n.UserEmail)
                .HasColumnName("UserEmail")
                .HasMaxLength(255)
                .IsRequired();

        
        }
    }
}
