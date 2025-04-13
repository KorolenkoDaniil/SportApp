using Newtonsoft.Json;
using SportAppServer.Entities.Models;
using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace SportAppServer.Models.Entities
{

    [Table("News")]
    public class News
    {
        [Key]
        public DateTime DateTime { get; set; }
        public required string Sport { get; set; }
        public required string Title { get; set; }
        public required string ImageId { get; set; }
        public required string ArticleText { get; set; }
        public required List<NewsTag> Tags { get; set; }


        public override string ToString()
        {
            return $"DateTime: {DateTime}, Sport: {Sport}, Title: {Title}, ImageId: {ImageId}";
        }
    }

}
