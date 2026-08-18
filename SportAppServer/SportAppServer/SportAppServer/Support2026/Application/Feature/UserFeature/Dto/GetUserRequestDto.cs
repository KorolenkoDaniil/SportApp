using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.Dto
{
    public class GetUserRequestDto
    {
        [JsonProperty("email")]
        public required string Email { get; set; }

        public override string ToString()
        {
            return $"{Email}";
        }
    }
}
