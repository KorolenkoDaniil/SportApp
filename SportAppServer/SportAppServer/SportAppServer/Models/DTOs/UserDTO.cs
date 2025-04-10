using Newtonsoft.Json;

namespace SportAppServer.Models.DTOs
{
    public class UserDTO
    {
        [JsonProperty("email")]
        public string UserEmail { get; set; }

        [JsonProperty("pictureId")]
        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";

        public UserDTO(string UserEmail, string UserImage)
        {
            this.UserEmail = UserEmail;
            this.UserImage = UserImage;
        }

       

    }
}
