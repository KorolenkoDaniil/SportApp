using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SportAppServer.Models.Entities
{
    [Table("Users")]
    public class User
    {
        [Key]
        public string UserEmail { get; set; }

        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";
        public bool IsWhiteTheme { get; set; } = true;

        public List<Comment> Comments { get; set; } = new();

        public User(string userEmail, string userImage, bool isWhiteTheme, List<Comment> comments)
        {
            UserEmail = userEmail;
            UserImage = userImage;
            IsWhiteTheme = isWhiteTheme;    
            Comments = comments;
        }

        public User() { }
    }
}
