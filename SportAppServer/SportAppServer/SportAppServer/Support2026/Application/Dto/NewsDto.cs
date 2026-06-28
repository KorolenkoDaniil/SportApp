using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace SportAppServer.Support2026.Application.Dto
{
    public class NewsDto
    {
        [JsonPropertyName("date_time")]
        public DateTime DateTime { get; set; }

        [JsonPropertyName("sport")]
        public string Sport { get; set; }

        [JsonPropertyName("title")]
        public string Title { get; set; }

        [JsonPropertyName("image_id")]
        public string ImageId { get; set; }

        [JsonPropertyName("text")]
        public string ArticleText { get; set; }      
    }

    public class NewsDtoFromParser
    {
        [JsonProperty("sport")]
        public string Sport { get; set; }

        [JsonProperty("date_time")]
        public DateTime DateTime { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("image_id")]
        public string ImageId { get; set; }

        [JsonProperty("article_texts")]
        public string ArticleTexts { get; set; }
    }

}
