using Newtonsoft.Json;


namespace SportAppServer.Models.DTOs.Requests
{
    public class EmailDto
    {
        [JsonProperty("email")]
        public required string Email { get; set; }
    }
}
