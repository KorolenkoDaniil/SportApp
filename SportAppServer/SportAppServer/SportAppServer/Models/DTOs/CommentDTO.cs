using System.Text.Json.Serialization;
using SportAppServer.Models.DTOs;

namespace SportAppServer.Models.Entities
{
    public class CommentDTO
    {
        [JsonPropertyName("comment_id")]
        public int CommentId { get; set; }

        [JsonPropertyName("news_date_time")]
        public DateTime NewsDateTime { get; set; }

        [JsonPropertyName("comment_date_time")]
        public DateTime CommentDateTime { get; set; }

        [JsonPropertyName("comment_text")]
        public string CommentText { get; set; }

        [JsonPropertyName("email")]
        public string UserEmail { get; set; }

        [JsonPropertyName("user")]
        public UserDTO User { get; set; }

        [JsonPropertyName("elapsed_ime")]
        public string ElapsedTime { get; set; }

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
