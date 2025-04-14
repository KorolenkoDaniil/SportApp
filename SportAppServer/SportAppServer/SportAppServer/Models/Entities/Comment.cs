using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace SportAppServer.Models.Entities
{
    [Table("NewsComments")]
    public class Comment
    {
        [Key]
        public int CommentId { get; set; }
        public required DateTime NewsDateTime { get; set; }
        public required DateTime CommentDateTime { get; set; }
        public required string CommentText { get; set; }
        public required string UserEmail { get; set; }
        public User User { get; set; }

        public override string ToString()
        {
            return $"CommentId: {CommentId}, NewsDateTime: {NewsDateTime}, CommentDateTime: {CommentDateTime}, CommentText: {CommentText}, UserEmail: {UserEmail}";
        }
    }
}
