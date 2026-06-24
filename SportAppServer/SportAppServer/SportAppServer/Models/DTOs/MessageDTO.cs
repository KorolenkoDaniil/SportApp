using Newtonsoft.Json;

namespace SportAppServer.Models.DTOs
{
    public class MessageDTO
    {

        [JsonProperty("user_email")]
        public string UserEmail { get; set; }

        [JsonProperty("message_text")]
        public string MessageText { get; set; }

        [JsonProperty("is_ai_answer")]
        public bool IsAiAnswer { get; set; }


        public MessageDTO() { }

        public MessageDTO(string userEmail, string messageText, bool isAiAnswer)
        {
            UserEmail = userEmail;
            MessageText = messageText;
            IsAiAnswer = isAiAnswer;
        }
    }
}
