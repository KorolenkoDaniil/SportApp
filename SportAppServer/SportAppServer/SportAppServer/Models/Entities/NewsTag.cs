using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using SportAppServer.Models.Entities;

namespace SportAppServer.Entities.Models
{
    [Table("news_tags")]
    public class NewsTag
    {
        [Key]
        public int TagId { get; set; }
        public string Tag { get; set; }
        public DateTime NewsDateTime { get; set; }
        public News news { get; set; }


        public NewsTag(string tag, DateTime newsDateTime)
        {
            Tag = tag;
            NewsDateTime = newsDateTime;
        }

        public override string ToString()
        {
            return $"Tag: {Tag}, NewsId: {NewsDateTime}";
        }
    }
}
