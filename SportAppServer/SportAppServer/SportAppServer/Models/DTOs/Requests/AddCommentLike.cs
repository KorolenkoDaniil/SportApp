namespace SportAppServer.Models.DTOs.Requests
{
    public class AddCommentLike
    {
        public string LikeAuthor { get; set; }
        public int CommentId { get; set; }
    }
}
