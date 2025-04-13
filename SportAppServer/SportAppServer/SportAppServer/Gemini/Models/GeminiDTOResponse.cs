using Newtonsoft.Json;
using System.Text.Json.Serialization;

namespace SportAppServer.Gemini.Models
{
    public class GeminiDTOResponse
    {
        [JsonProperty("answer")]
        public string Response { get; set; }

        public GeminiDTOResponse(string prompt)
        {
            Response = prompt;
        }   
    }
}
