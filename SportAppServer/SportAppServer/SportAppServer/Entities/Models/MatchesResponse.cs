using Newtonsoft.Json;

namespace SportAppServer.Entities.Models
{
    public class MatchesResponse
    {
        [JsonProperty("page_index")]
        public int PageIndex { get; set; }

        [JsonProperty("page_size")]
        public int PageSize { get; set; }

        [JsonProperty("items_count")]
        public int PageCount { get; set; }

        [JsonProperty("is_last_page")]
        public bool IsLastPage { get; set; }

        [JsonProperty("items")]
        public required List<SportMatch> Items { get; set; }

    }
}
