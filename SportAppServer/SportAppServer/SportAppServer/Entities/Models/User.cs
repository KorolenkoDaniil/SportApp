using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SportAppServer.Entities.Models
{
    [Table("Users")]
    public class User
    {
        public User() { }
        public User(string UserEmail, string UserImage) {
            this.UserEmail = UserEmail; 
            this.UserImage = UserImage;
        }

        [Key]
        [JsonProperty("email")]
        public string UserEmail { get; set; }

        [JsonProperty("pictureId")]
        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";

    }
}
