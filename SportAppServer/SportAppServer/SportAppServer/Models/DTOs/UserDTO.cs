//using Microsoft.AspNetCore.Mvc.ModelBinding.Validation;
//using SportAppServer.Models.Entities;
//using System.Text.Json.Serialization;

//namespace SportAppServer.Models.DTOs
//{
//    public class UserDTO
//    {
//        [JsonPropertyName("email")]
//        public string UserEmail { get; set; }

//        [JsonPropertyName("pictureId")]
//        public string UserImage { get; set; } = "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg";
        
//        [JsonPropertyName("is_white_theme")]
//        public bool IsWhiteTheme { get; set; } = true;

//        [JsonIgnore]
//        [ValidateNever]
//        public List<CommentDTO> Comments { get; set; }


//        public override string ToString()
//        {
//            return $"UserEmail  {UserEmail} UserImage {UserImage} IsWhiteTheme {IsWhiteTheme}";
//        }

//        public UserDTO(string userEmail, string userImage, bool isWhiteTheme, List<CommentDTO> comments)
//        {
//            UserEmail = userEmail;
//            UserImage = userImage;
//            IsWhiteTheme = isWhiteTheme;
//            Comments = comments;
//        }

//        public UserDTO() { }
//    }
//}
