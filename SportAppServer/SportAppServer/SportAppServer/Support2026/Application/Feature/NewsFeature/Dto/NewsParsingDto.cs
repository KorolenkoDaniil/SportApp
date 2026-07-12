using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.NewsFeature.Dto
{
    public class NewsParsingDto
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
