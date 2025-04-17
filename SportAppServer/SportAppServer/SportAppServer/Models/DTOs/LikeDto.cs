using Newtonsoft.Json;

namespace SportAppServer.Models.Entities
{
    public class LikeDto
    {
        [JsonProperty("news_date_time")]
        public required DateTime NewsDateTime { get; set; }

        [JsonProperty("user_email")]
        public required string UserEmail { get; set; }

      

        public LikeDto() { }

        public LikeDto(DateTime newsDateTime, string userEmail)
        {
            NewsDateTime = newsDateTime;
            UserEmail = userEmail;
        }

        public override string ToString()
        {
            return $"NewsDateTime: {NewsDateTime}";
        }

    }
}
