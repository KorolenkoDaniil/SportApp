namespace SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities
{
    public class News
    {
        public int Id { get; set; }
        public DateTime DateTime { get; set; }
        public string Sport { get; set; }
        public string Title { get; set; }
        public string ImageId { get; set; }
        public string ArticleText { get; set; }
        public string TextAfterLemmatize { get; set; }
    }

}
