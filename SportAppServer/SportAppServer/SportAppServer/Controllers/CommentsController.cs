using Microsoft.AspNetCore.Mvc;
using SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.CommentsFeature.Mappers;
using SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases;
using SportAppServer.Support2026.Application.Feature.Pagination;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;
using System.Diagnostics;




//todo сипарвить падение если сотавили коммент в с почтой не зи бахы



namespace SportAppServer.Controllers
{
    [Route("Comments")]
    [ApiController]
    public class CommentsController : ControllerBase
    {
        private readonly AddNewCommentUseCase _addNewCommentUseCase;
        private readonly GetPaginatedCommentsList _getPaginatedCommentsList;
        private readonly DeleteCommentUseCase _deleteCommentUseCase;
        private readonly EditCommentUseCase _editCommentUseCase;

        public CommentsController(
            AddNewCommentUseCase addNewCommentUseCase,
            GetPaginatedCommentsList getPaginatedCommentsList,
            DeleteCommentUseCase deleteCommentUseCase,
            EditCommentUseCase editCommentUseCase)
        {
            _addNewCommentUseCase = addNewCommentUseCase;
            _getPaginatedCommentsList = getPaginatedCommentsList;
            _deleteCommentUseCase = deleteCommentUseCase;
            _editCommentUseCase = editCommentUseCase;
        }

        [HttpGet("GetComments")]
        public async Task<IActionResult> GetComments(
            [FromQuery] string? email,
            [FromQuery] int newsId,
            [FromQuery] int pageNumber = 1,
            [FromQuery] int pageSize = 10)
        {
            try
            {
                Debug.WriteLine($"[GetComments] newsId: {newsId}, pageNumber: {pageNumber}, pageSize: {pageSize}");

                PaginatedList<CommentDtoResponse> paginatedList =
                    await _getPaginatedCommentsList.Execute(pageNumber, pageSize, email, newsId);

                if (paginatedList.ItemsList == null)
                {
                    Debug.WriteLine("[GetComments] paginatedList.ItemsList is null");
                    return StatusCode(500, "Ошибка: данные не получены");
                }

                if (paginatedList.ItemsList.Count == 0)
                {
                    Debug.WriteLine($"[GetComments] Нет комментариев для новости с id {newsId}");
                    return NotFound();
                }

                return Ok(paginatedList);
            }
            catch (Exception ex)
            {
                Debug.WriteLine($"[GetComments] Ошибка: {ex.Message}");
                Debug.WriteLine(ex.StackTrace);
                return StatusCode(500, "Внутренняя ошибка сервера");
            }
        }

        [HttpPost("AddNewComment")]
        public async Task<IActionResult> PutComment([FromBody] CommentDtoRequest commentDtoRequest)
        {
            if (commentDtoRequest == null || string.IsNullOrWhiteSpace(commentDtoRequest.CommentText))
                return BadRequest("Текст комментария не может быть пустым");

            Comment? comment = await _addNewCommentUseCase.AddNewsComment(commentDtoRequest);

            if (comment == null)
                return BadRequest("Не удалось добавить комментарий");

            CommentDtoResponse commentDtoResponse = comment.ToResponseDto();

            return Ok(commentDtoResponse);
        }

        [HttpDelete("DeleteComment/{commentId:int}")]
        public async Task<IActionResult> DeleteComment([FromRoute] int commentId)
        {
            if (commentId < 1)
                return BadRequest("Неверный id комментария");

            bool isDeleted = await _deleteCommentUseCase.Execute(commentId);
            if (!isDeleted)
                return NotFound("Комментарий не найден");

            return Ok(true);
        }

        [HttpPut("EditComment")]
        public async Task<IActionResult> EditComment([FromBody] EditCommentRequest request)
        {
            if (request == null || request.CommentId < 1)
                return BadRequest("Неверный id комментария");

            if (string.IsNullOrWhiteSpace(request.NewText))
                return BadRequest("Комментарий пуст");

            bool isEdited = await _editCommentUseCase.Execute(request.CommentId, request.NewText);
            if (!isEdited)
                return NotFound("Комментарий не найден");

            return Ok(true);
        }
    }

}