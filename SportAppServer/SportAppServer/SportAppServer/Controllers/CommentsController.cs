using Microsoft.AspNetCore.Mvc;
using SportAppServer.Models.DTOs.Requests;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Pagination;
using SportAppServer.Services;

namespace SportAppServer.Controllers
{
    [Route("Comments")]
    [ApiController]
    public class CommentsController : Controller
    {
        private readonly ICommentsService _commentsService;

        public CommentsController(ICommentsService commentsService)
        {
            _commentsService = commentsService;
        }


        [HttpGet("GetComments")]
        public async Task<IActionResult> GetComments([FromQuery] DateTime itemId, int pageNumber = 1, int pageSize = 10)
        {
            CommentsPagination paginatedComments = await _commentsService.GetPaginatedCommentsList(itemId, pageNumber, pageSize);

            return Ok(paginatedComments);
        }



        [HttpPost("PutComment")]
        public async Task<IActionResult> PutComment ([FromBody] CommentDTO comment)
        {
            CommentDTO commentDTO = await _commentsService.PutCommment(comment);

            return Ok(commentDTO);
        }




        [HttpPost("AddLike")]
        public async Task<IActionResult> AddLike([FromBody] AddCommentLike likeData)
        {
            if (string.IsNullOrEmpty(likeData.LikeAuthor))
                return BadRequest();

            int likesCount = await _commentsService.AddLike(likeData.LikeAuthor, likeData.CommentId);

            if (likesCount < 0)
            {
                return BadRequest();
            }

            return Ok(likesCount);
        }


        //[HttpPost("RemoveLike")]
        //public async Task<IActionResult> RemoveLike([FromBody] LikeDto like)
        //{
        //    if (like == null)
        //        return BadRequest();

        //    int likesCount = await _likeService.RemoveLikeAsync(like);

        //    if (likesCount < 0)
        //    {
        //        return BadRequest();
        //    }

        //    return Ok();
        //}


    }
}
