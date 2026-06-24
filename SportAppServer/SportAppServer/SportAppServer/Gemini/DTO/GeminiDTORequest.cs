using Newtonsoft.Json;

namespace SportAppServer.Gemini.DTO
{
    public class GeminiDTORequest
    {
        [JsonProperty("email")]
        public string Email { get; set; }

        [JsonProperty("prompt")]
        public string Prompt { get; set; }



        public GeminiDTORequest() { }

        public GeminiDTORequest(string email, string prompt)
        {
            Email = email;
            Prompt = prompt;
        }
    }
}
