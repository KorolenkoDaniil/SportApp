using Microsoft.AspNetCore.Mvc;
using SportAppServer.Entities.context;
using SportAppServer.Entities.Models;
using SportAppServer.Entities.Models.dto;
using System.Diagnostics;

namespace SportAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsersController : Controller
    {
        private readonly DBContext _dbContext;

        public UsersController(DBContext dbContext)
        {
            _dbContext = dbContext;
        }

        [HttpPost]
        public async Task<IActionResult> PutUser([FromBody] EmailDto email)
        {
            Debug.WriteLine(email.Email);
            if (string.IsNullOrEmpty(email.Email))
            {
                return BadRequest("Email is required.");
            }

            var newUser = new User(email.Email, "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg");
            _dbContext.Users.Add(newUser);
            await _dbContext.SaveChangesAsync();

            return Ok(newUser);
        }



        [HttpGet("GetUser")]
        public async Task<IActionResult> GetUserData(string email)
        {
            if (string.IsNullOrEmpty(email))
            {
                return BadRequest("Email is required.");
            }

            var user = await _dbContext.Users.FindAsync(email);
            if (user == null)
            {
                return NotFound("User not found.");
            }

            return Ok(user);
        }
    }

  
}


//AIzaSyDPL0vKJ - JcFSGjF2jPcQabpTkx7zBMyYQ
