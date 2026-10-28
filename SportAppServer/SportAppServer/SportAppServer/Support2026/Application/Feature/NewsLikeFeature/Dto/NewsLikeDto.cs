using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.NewsLikeFeature.Dto
{
    public class NewsLikeDto
    {
        [JsonProperty("newsId")]
        public required int NewsId { get; set; }

        [JsonProperty("userEmail")]
        public required string UserEmail { get; set; }
    }
}
