using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto
{
    public class CommentDtoResponse
    {
        [JsonProperty("commentId")]
        public int CommentId { get; set; }

        [JsonProperty("commentText")]
        public string CommentText { get; set; } = "";

        [JsonProperty("newsId")]
        public int NewsId { get; set; }

        [JsonProperty("email")]
        public string Email { get; set; } = "";

        [JsonProperty("commentDateTime")]
        public DateTime CommentDateTime { get; set; }
    }
}
