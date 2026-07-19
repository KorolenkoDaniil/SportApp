using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Entities;

namespace SportAppServer.Support2026.Infrastructure.Configuration
{
    public class UserConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder.ToTable("AppUser");

            builder.HasKey(u => u.Id);
            builder.Property<int>("Id")
                .HasColumnName("Id")
                .ValueGeneratedOnAdd();

            builder.Property(u => u.UserName)
                .HasColumnName("UserName")
                .HasMaxLength(128)
                .IsRequired();

            builder.Property(u => u.Email)
                .HasColumnName("Email")
                .HasMaxLength(128)
                .IsRequired();

            builder.Property(u => u.ImageId)
               .HasColumnName("ImageId");
    }
    }
}
