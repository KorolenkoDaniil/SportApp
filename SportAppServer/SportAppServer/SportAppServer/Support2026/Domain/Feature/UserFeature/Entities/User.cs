namespace SportAppServer.Support2026.Domain.Feature.UserFeature.Entities
{
    public class User
    {
        public int Id { get; set; }
        public required string UserName { get; set; } = "";
        public required string Email { get; set; }
        public string ImageId { get; set; } = "";
    }


}
