namespace SportAppServer.Gemini.Models
{
    public class GeminiDTOResponse
    {
        public string Prompt { get; set; }

        public GeminiDTOResponse(string prompt)
        {
            Prompt = prompt;
        }   
    }
}
