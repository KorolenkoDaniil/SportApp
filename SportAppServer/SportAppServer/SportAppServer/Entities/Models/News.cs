using Newtonsoft.Json;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace SportAppServer.Entities.models
{
    [Table("News")]
    public class News
    {
        [Key]
        [JsonProperty("date_time")]
        public DateTime DateTime { get; set; }

        [JsonProperty("sport")]
        public required string Sport { get; set; }

        [JsonProperty("title")]
        public required string Title { get; set; }

        [JsonProperty("image_id")]
        public required string ImageId { get; set; }

        [JsonProperty("article_texts")]
        public required string ArticleText { get; set; }



        public override string ToString()
        {
            return $"DateTime: {DateTime}, Sport: {Sport}, Title: {Title}, ImageId: {ImageId}";
        }
    }
}
