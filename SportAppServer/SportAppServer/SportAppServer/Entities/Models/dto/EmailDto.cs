using Newtonsoft.Json;


namespace SportAppServer.Entities.Models.dto
{
    public class EmailDto
    {
        [JsonProperty("email")]
        public string Email { get; set; }
    }
}
