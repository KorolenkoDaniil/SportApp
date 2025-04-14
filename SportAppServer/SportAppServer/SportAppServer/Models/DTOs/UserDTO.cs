using Newtonsoft.Json;
using SportAppServer.Models.Entities;

namespace SportAppServer.Models.DTOs
{
    public class UserDTO
    {
        [JsonProperty("email")]
        public string UserEmail { get; set; }

        [JsonProperty("pictureId")]
        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";

        [System.Text.Json.Serialization.JsonIgnore]
        public List<CommentDTO> Comments { get; set; }



        public UserDTO(string UserEmail, string UserImage, List<CommentDTO> comments)
        {
            this.UserEmail = UserEmail;
            this.UserImage = UserImage;
            Comments = comments;
        }

        public UserDTO() { }
       

    }
}
