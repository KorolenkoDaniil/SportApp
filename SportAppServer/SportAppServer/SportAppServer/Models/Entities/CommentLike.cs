using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SportAppServer.Models.Entities
{
    [Table("CommentLikes")]
    public class CommentLike
    {
        [Key]
        public int CommentLikeId { get; set; }
        public int CommentId { get; set; }
        public string LikedByUserEmail { get; set; }

        [ForeignKey("CommentId")]
        public virtual Comment Comment { get; set; }

        [ForeignKey("LikedByUserEmail")]
        public virtual User LikeAuthor { get; set; }

        public CommentLike() { }
        public CommentLike(int commentId, string likedByUserEmail, Comment comment, User likeAuthor)
        {
            CommentId = commentId;
            LikedByUserEmail = likedByUserEmail;
            Comment = comment;
            LikeAuthor = likeAuthor;
        }

        public override string ToString()
        {
            return $"CommentLikeId: {CommentLikeId}, CommentId: {CommentId}, LikedByUserEmail: {LikedByUserEmail}";
        }
    }
}
