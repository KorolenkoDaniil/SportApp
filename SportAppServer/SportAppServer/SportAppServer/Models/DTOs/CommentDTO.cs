using Newtonsoft.Json;


namespace SportAppServer.Models.Entities
{
    public class CommentDTO
    {
        [JsonProperty("comment_id")]
        public int CommentId { get; set; }

        [JsonProperty("news_date_time")]
        public DateTime NewsDateTime { get; set; }

        [JsonProperty("comment_date_time")]
        public  DateTime CommentDateTime { get; set; }

        [JsonProperty("comment_text")]
        public  string CommentText { get; set; }

        [JsonProperty("user_email")]
        public  string UserEmail { get; set; }



        public CommentDTO(
            int commentId, DateTime newsDateTime, DateTime commentDateTime, string commentText, string userEmail)
        {
            CommentId = commentId;
            NewsDateTime = newsDateTime;
            CommentDateTime = commentDateTime;
            CommentText = commentText;
            UserEmail = userEmail;
        }

        public override string ToString()
        {
            return $"CommentId: {CommentId}, NewsDateTime: {NewsDateTime}, CommentDateTime: {CommentDateTime}, CommentText: {CommentText}, UserEmail: {UserEmail}";
        }
    }
}
