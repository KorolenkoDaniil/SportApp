using Microsoft.AspNetCore.Mvc;
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


    }
}
