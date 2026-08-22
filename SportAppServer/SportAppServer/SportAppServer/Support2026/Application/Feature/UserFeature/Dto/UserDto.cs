using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Feature.UserFeature.Dto
{
    public class UserRequestDto
    {
        //[JsonProperty("userName")]
        //public required string UserName { get; set; }

        [JsonProperty("email")]
        public required string Email { get; set; }


        public override string ToString()
        {
            //return $"{UserName} {Email}";
            return $"{Email}";
        }
    }

 
    public class UserResponseDto
    {
        [JsonProperty("id")]
        public int Id { get; set; }

        [JsonProperty("userName")]
        public required string UserName { get; set; }

        [JsonProperty("email")]
        public required string Email { get; set; }

        [JsonProperty("imageId")]
        public required string ImageId { get; set; }


        public override string ToString()
        {
            return $"{Id} {UserName} {Email} {ImageId}";
        }
    }
}