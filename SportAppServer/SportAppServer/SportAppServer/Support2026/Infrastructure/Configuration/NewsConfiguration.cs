using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;

namespace SportAppServer.Support2026.Infrastructure.Configuration
{
    public class NewsConfiguration : IEntityTypeConfiguration<News>
    {
        public void Configure(EntityTypeBuilder<News> builder)
        {
            builder.ToTable("News");

            builder.HasKey(n => n.Id);
            builder.Property<int>("Id")
                .HasColumnName("Id")
                .ValueGeneratedOnAdd();

            builder.Property(n => n.DateTime)
                 .HasColumnName("DateTime")
                 .IsRequired();

            builder.Property(n => n.Sport)
                .HasColumnName("Sport")
                .HasMaxLength(255)
                .IsRequired();

            builder.Property(n => n.Title)
                .HasColumnName("Title")
                .HasMaxLength(200)
                .IsRequired();

            builder.Property(n => n.ImageId)
                .HasColumnName("ImageId")
                .HasMaxLength(300)
                .IsRequired();

            builder.Property(n => n.ArticleText)
                .HasColumnName("ArticleText");

            builder.Property(n => n.TextAfterLemmatize)
                .HasColumnName("TextAfterLemmatize");

            
        }
    }
}
