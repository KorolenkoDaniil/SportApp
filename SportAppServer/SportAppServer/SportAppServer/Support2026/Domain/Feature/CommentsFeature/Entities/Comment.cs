namespace SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities
{
    public class Comment
    {
        public Comment() { }
        public Comment(string commentText, int newsId, string email)
        {
            CommentText = commentText;
            NewsId = newsId;
            Email = email;
        }

        public int CommentId { get; set; }
        public string CommentText { get; set; } = "";
        public int NewsId { get; set; }
        public string Email { get; set; } = "";
        public DateTime CommentDateTime { get; set; } 
    }
}
