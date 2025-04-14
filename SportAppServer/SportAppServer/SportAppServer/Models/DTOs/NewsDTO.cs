using Newtonsoft.Json;


namespace SportAppServer.Models.DTOs
{
    public class NewsDTO
    {
        [JsonProperty("date_time")]
        public DateTime DateTime { get; set; }

        [JsonProperty("sport")]
        public string Sport { get; set; }

        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("image_id")]
        public string ImageId { get; set; }

        [JsonProperty("article_texts")]
        public string ArticleText { get; set; }

        [JsonProperty("comments_count")]
        public int Comments_count { get; set; }

        [JsonProperty("likes_count")]
        public int Likes_count { get; set; }

        [JsonProperty("Tags")]
        public List<NewsTagDTO> Tags { get; set; }




        public NewsDTO(DateTime dateTime, string sport, string title, string imageId, string articleText, List<NewsTagDTO> tags)
        {
            DateTime = dateTime;
            Sport = sport;
            Title = title;
            ImageId = imageId;
            ArticleText = articleText;
            Tags = tags;
        }

        public override string ToString()
        {
            return $"DateTime: {DateTime}, Sport: {Sport}, Title: {Title}, ImageId: {ImageId}";
        }
    }
}
