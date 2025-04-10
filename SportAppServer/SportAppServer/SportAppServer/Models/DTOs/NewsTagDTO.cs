using Newtonsoft.Json;

namespace SportAppServer.Models.DTOs
{
    public class NewsTagDTO
    {

        [JsonProperty("tag")]
        public string Tag { get; set; }

        [JsonProperty("news_date_time")]
        public DateTime NewsDateTime { get; set; }


        public NewsTagDTO(string tag, DateTime newsDateTime)
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

