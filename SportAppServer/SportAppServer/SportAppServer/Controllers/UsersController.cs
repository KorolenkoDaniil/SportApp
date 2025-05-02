using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using SportAppServer.Models.DTOs;
using SportAppServer.Models.DTOs.Requests;
using SportAppServer.Services;
using System.Diagnostics;


namespace SportAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : Controller
    {
        private readonly IUserService _userService;
        
        public UsersController(IUserService userService)
        {
            _userService = userService;
        }


        [HttpPost]
        public async Task<IActionResult> PutUser([FromBody] EmailDto email)
        {
            Debug.WriteLine(email.Email);

            UserDTO user = await _userService.PutUser(email);

            if (user != null)
                return Ok(user);
            else
                return BadRequest();

        }



        [HttpGet("GetUser")]
        public async Task<IActionResult> GetUserData(string email)
        {
            UserDTO user = await _userService.GetUserData(email);

            if (user != null)
                return Ok(user);
            else
                return BadRequest();
        }



        [HttpPost("putUserImage")]
        public async Task<IActionResult> PutUserImage([FromForm] IFormFile image, [FromForm] string email)
        {
            if (image != null && image.Length > 0)
            {
                string imageId = await _userService.PutUserImage(email, image);
                if (imageId.IsNullOrEmpty())
                {
                    return BadRequest("User not found.");
                }
          
                return Ok(imageId);
            }

            return BadRequest("No file uploaded.");
        }
    }
  
}


