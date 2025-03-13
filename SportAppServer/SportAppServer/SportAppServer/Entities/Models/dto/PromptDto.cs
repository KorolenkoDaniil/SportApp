using Newtonsoft.Json;


namespace SportAppServer.Entities.Models.dto
{
    public class PromptDto
    {
        [JsonProperty("prompt")]
        public string Prompt { get; set; }
    }
}
