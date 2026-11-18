using Microsoft.AspNetCore.Mvc;
using SportAppServer.Support2026.Application.Feature.NewsLikeFeature.UseCases;

[Route("api/[controller]")]
[ApiController]
public class NewsLikeController : ControllerBase 
{
    private readonly UpdateNewsLikeStatusUseCase _updateNewsLikeStatusUseCase;

    public NewsLikeController(UpdateNewsLikeStatusUseCase updateNewsLikeStatusUseCase)
    {
        _updateNewsLikeStatusUseCase = updateNewsLikeStatusUseCase;
    }

    [HttpPost("toggle/{newsId}")]
    public async Task<IActionResult> UpdateNewsLikeStatus(
        [FromRoute] int newsId,
        [FromQuery] string email) 
    {
        try
        {
            await _updateNewsLikeStatusUseCase.Execute(newsId, email);
            return Ok(new { success = true });
        }
        catch (Exception ex)
        {
            return StatusCode(500, $"Internal server error: {ex.Message}");
        }
    }
}