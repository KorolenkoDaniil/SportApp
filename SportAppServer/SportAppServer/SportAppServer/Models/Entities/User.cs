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
        public List<Comment> Comments { get; set; }

        public User(string UserEmail, string UserImage, List<Comment> comments)
        {
            this.UserEmail = UserEmail;
            this.UserImage = UserImage;
            this.Comments = comments;
        }

        public User() { }
    }
}
