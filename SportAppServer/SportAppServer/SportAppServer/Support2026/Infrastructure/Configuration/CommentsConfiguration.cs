using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;

namespace SportAppServer.Support2026.Infrastructure.Configuration
{
    public class CommentsConfiguration : IEntityTypeConfiguration<Comment>
    {
        public void Configure(EntityTypeBuilder<Comment> builder)
        {
            builder.ToTable("Comments");

            builder.HasKey(com => com.CommentId);
            builder.Property<int>("CommentId")
                .HasColumnName("CommentId")
                .ValueGeneratedOnAdd();

            builder.Property(com => com.CommentText)
                .HasColumnName("CommentText")
                .HasMaxLength(2000)
                .IsRequired();

            builder.Property(com => com.NewsId)
                .HasColumnName("NewsId")
                .IsRequired();

            builder.Property(com => com.Email)
               .HasColumnName("Email")
               .HasMaxLength(128)
               .IsRequired();

            builder.Property(com => com.CommentDateTime)
                .HasColumnName("CommentDateTime")
                .IsRequired()
                .HasDefaultValueSql("GETDATE()");


            builder.HasOne<News>()
                .WithMany()
                .HasForeignKey(com => com.NewsId)
                .OnDelete(DeleteBehavior.Cascade);


            builder.HasIndex(com => new { com.NewsId , com.Email})
               .HasDatabaseName("IX_NewsComments_NewsId_UserEmail");
        }
    }
}


