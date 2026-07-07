using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace SportAppServer.Support2026.Application.Dto
{
    public class NewsDto
    {
        [JsonProperty("date_time")]
        public DateTime DateTime { get; set; }

        [JsonProperty("sport")]
        public string Sport { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("image_id")]
        public string ImageId { get; set; }

        [JsonProperty("text")]
        public string ArticleText { get; set; }      
    }
}
