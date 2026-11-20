using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto
{
    public class EditCommentRequest
    {
        [JsonProperty("commentId")]
        public int CommentId { get; set; }

        [JsonProperty("newText")]
        public string NewText { get; set; } = "";

    }
}
