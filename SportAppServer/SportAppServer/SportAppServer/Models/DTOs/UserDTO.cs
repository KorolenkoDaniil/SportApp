using Microsoft.AspNetCore.Mvc.ModelBinding.Validation;
using SportAppServer.Models.Entities;
using System.Text.Json.Serialization;

namespace SportAppServer.Models.DTOs
{
    public class UserDTO
    {
        [JsonPropertyName("email")]
        public string UserEmail { get; set; }

        [JsonPropertyName("pictureId")]
        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";

        [JsonIgnore]
        [ValidateNever]
        public List<CommentDTO> Comments { get; set; }

        public UserDTO(string userEmail, string userImage, List<CommentDTO> comments)
        {
            UserEmail = userEmail;
            UserImage = userImage;
            Comments = comments;
        }

        public UserDTO() { }
    }
}
