using SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.Mappers;

public static class CommentMapper
{
    public static Comment ToEntity(this CommentDtoRequest request)
    {
        ArgumentNullException.ThrowIfNull(request);

        return new Comment
        {
            CommentText = request.CommentText.Trim(),
            NewsId = request.NewsId,
            Email = request.Email.Trim()
        };
    }

    public static Comment ToEntity(this CommentDtoResponse response)
    {
        ArgumentNullException.ThrowIfNull(response);

        return new Comment
        {
            CommentId = response.CommentId,
            CommentText = response.CommentText.Trim(),
            NewsId = response.NewsId,
            Email = response.Email.Trim(),
            CommentDateTime = response.CommentDateTime
        };
    }

    public static CommentDtoResponse ToResponseDto(this Comment comment)
    {
        ArgumentNullException.ThrowIfNull(comment);

        return new CommentDtoResponse
        {
            CommentId = comment.CommentId,
            CommentText = comment.CommentText,
            NewsId = comment.NewsId,
            Email = comment.Email,
            CommentDateTime = comment.CommentDateTime
        };
    }

    public static List<CommentDtoResponse> ToResponseDtoList(this IEnumerable<Comment> comments)
    {
        ArgumentNullException.ThrowIfNull(comments);

        return comments.Select(ToResponseDto).ToList();
    }
}