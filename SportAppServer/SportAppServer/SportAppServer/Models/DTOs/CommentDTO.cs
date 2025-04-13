using Newtonsoft.Json;
using SportAppServer.Models.DTOs;


namespace SportAppServer.Models.Entities
{
    public class CommentDTO
    {
        [JsonProperty("comment_id")]
        public int CommentId { get; set; }

        [JsonProperty("news_date_time")]
        public DateTime NewsDateTime { get; set; }

        [JsonProperty("comment_date_time")]
        public DateTime CommentDateTime { get; set; }

        [JsonProperty("comment_text")]
        public string CommentText { get; set; }

        [System.Text.Json.Serialization.JsonIgnore]
        public string UserEmail { get; set; }

        [JsonProperty("user")]
        public UserDTO User { get; set; }  // Включаем данные пользователя


        public CommentDTO() { }

        public CommentDTO(int commentId, DateTime newsDateTime, DateTime commentDateTime, string commentText, string userEmail, UserDTO user)
        {
            CommentId = commentId;
            NewsDateTime = newsDateTime;
            CommentDateTime = commentDateTime;
            CommentText = commentText;
            UserEmail = userEmail;
            User = user;
        }

        public override string ToString()
        {
            return $"CommentId: {CommentId}, NewsDateTime: {NewsDateTime}, CommentDateTime: {CommentDateTime}, CommentText: {CommentText}, UserEmail: {UserEmail}";
        }
    }
}
