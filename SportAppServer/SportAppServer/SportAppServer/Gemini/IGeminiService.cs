using SportAppServer.Gemini.Models;

namespace SportAppServer.Gemini
{
    public interface IGeminiService
    {
        Task<List<string>> CreateTags(string newsText);
        Task<GeminiDTOResponse> AskGemini(GeminiDTORequest prompt);
    }
}
