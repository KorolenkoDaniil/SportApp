using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;


namespace SportAppServer.Models.Entities
{
    [Table("NewsLike")]

    public class Like
    {
        [Key]
        public int LikeId { get; set; }
        public required DateTime NewsDateTime { get; set; }
        public required string UserEmail { get; set; }



        public override string ToString()
        {
            return $"LikeId: {LikeId}, NewsDateTime: {NewsDateTime}, UserEmail: {UserEmail}";
        }

    }
}
