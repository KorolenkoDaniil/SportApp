using Microsoft.AspNetCore.Mvc;
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
        public async Task<IActionResult> GetComments([FromQuery] DateTime itemId, int pageNumber = 1, int pageSize = 50)
        {
            CommentsPagination paginatedComments = await _commentsService.GetPaginatedCommentsList(itemId, pageNumber, pageSize);

            if (paginatedComments.Comments.Count() == 0)
            {
                return NotFound(new { message = $"No comments found for itemId {itemId}" });
            }

            return Ok(paginatedComments);
        }
    }
}
