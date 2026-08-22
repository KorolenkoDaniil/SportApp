using Microsoft.AspNetCore.Mvc;
using SportAppServer.Support2026.Application.Feature.UserFeature.Dto;
using SportAppServer.Support2026.Application.Feature.UserFeature.UseCases;
using SportAppServer.Support2026.Domain.Feature.UserFeature.Repository;
using System.Diagnostics;


namespace SportAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : Controller
    {
        private readonly AddUserToDbUseCase _addUserToDbUseCase;
        private readonly GetUserByIdUseCase _getUserByIdUseCase;
        private readonly GetUserByEmailUseCase _getUserByEmailUseCase;
        private readonly CheckUserExistsUseCase _checkUserExistsUseCase;

        public UserController(AddUserToDbUseCase addUserToDbUseCase, GetUserByIdUseCase getUserByIdUseCase, 
            GetUserByEmailUseCase getUserByEmailUseCase, CheckUserExistsUseCase checkUserExistsUseCase)
        {
            _addUserToDbUseCase = addUserToDbUseCase;
            _getUserByEmailUseCase = getUserByEmailUseCase;
            _checkUserExistsUseCase = checkUserExistsUseCase;
            _getUserByIdUseCase = getUserByIdUseCase;
        }


        [HttpPost("AddUser")]
        public IActionResult AddUser([FromBody] UserRequestDto userRequestDto)
        {
            Debug.WriteLine("UsersController AddUser " + userRequestDto);
           
            var(user, result) = _addUserToDbUseCase.Execute(userRequestDto);

            if (result == AddUserResult.Success && user != null)
            {
                return Created(string.Empty, user);
            }
            else if (result == AddUserResult.AlreadyExists && user != null)
                return Conflict(new { message = "Пользователь с таким email уже существует.", user });
            else
                return BadRequest("Не удалось зарегистрировать пользователя");
                
        }


        [HttpGet("exists")]
        public async Task<IActionResult> CheckUserExists([FromQuery] string email)
        {
            bool exists = await _checkUserExistsUseCase.Execute(email);
            return Ok(exists); 
        }



        [HttpPost("GetUser")]
        public async Task<IActionResult> GetUserData([FromBody] GetUserRequestDto dto)
        {

            Debug.WriteLine("UsersController GetUserData " + dto + "!!!!"); 

            UserResponseDto? user = await _getUserByEmailUseCase.Execute(dto.Email);

            if (user != null)
                return Ok(user);
            else
                return BadRequest();
        }



        //[HttpPost("putUserImage")]
        //public async Task<IActionResult> PutUserImage([FromForm] IFormFile image, [FromForm] string email)
        //{
        //    if (image != null && image.Length > 0)
        //    {
        //        string imageId = await _userService.PutUserImage(email, image);
        //        if (imageId.IsNullOrEmpty())
        //        {
        //            return BadRequest("User not found.");
        //        }

        //        return Ok(imageId);
        //    }

        //    return BadRequest("No file uploaded.");
        //}


        //[HttpPut("changeTheme")]
        //public async Task<IActionResult> ChangeTheme([FromQuery] string email)
        //{
        //    UserDTO user = await _userService.ChangeTheme(email);

        //    if (user != null)
        //        return Ok(user.IsWhiteTheme);
        //    else
        //        return BadRequest();
        //}
    }
}


//TODO нету стандатной аватарки