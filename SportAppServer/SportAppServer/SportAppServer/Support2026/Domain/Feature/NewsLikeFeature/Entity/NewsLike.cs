
namespace SportAppServer.Support2026.Domain.Feature.LikeFeature
{
    public class NewsLike
    {
        public int LikeId { get; set; }
        public required int NewsId { get; set; }
        public required string UserEmail { get; set; }

        public override string ToString()
        {
            return $"LikeId: {LikeId}, NewsId: {NewsId}, UserEmail: {UserEmail}";
        }

    }
}