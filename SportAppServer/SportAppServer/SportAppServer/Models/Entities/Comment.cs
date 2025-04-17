using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SportAppServer.Models.Entities
{
    [Table("NewsComments")]
    public class Comment
    {
        [Key]
        public int CommentId { get; set; }

        public DateTime NewsDateTime { get; set; }
        public DateTime CommentDateTime { get; set; }
        public string CommentText { get; set; }
        public string UserEmail { get; set; }
        public int LikesCount { get; set; }

        [ForeignKey("UserEmail")]
        public User User { get; set; }

        public override string ToString()
        {
            return $"CommentId: {CommentId}, NewsDateTime: {NewsDateTime}, CommentDateTime: {CommentDateTime}, CommentText: {CommentText}, UserEmail: {UserEmail}";
        }
    }
}
