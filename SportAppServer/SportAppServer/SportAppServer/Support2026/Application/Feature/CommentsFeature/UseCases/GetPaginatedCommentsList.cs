using SportAppServer.Support2026.Application.Feature.CommentsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.CommentsFeature.Mappers;
using SportAppServer.Support2026.Application.Feature.Pagination;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.CommentsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.CommentsFeature.UseCases
{
    public class GetPaginatedCommentsList
    {
        private readonly ICommentRepository _repository;
        private readonly IPaginationBuilder<CommentDtoResponse> _paginationBuilder;


        public GetPaginatedCommentsList(
            ICommentRepository commentRepository,
            IPaginationBuilder<CommentDtoResponse> paginationBuilder)
        {
            _repository = commentRepository;
            _paginationBuilder = paginationBuilder;
        }


        public IPaginationBuilder<CommentDtoResponse> Get_paginationBuilder()
        {
            return (PaginationBuilder<CommentDtoResponse>)_paginationBuilder;
        }

        public async Task<PaginatedList<CommentDtoResponse>> Execute(
            int pageNumber,
            int pageSize,
            string? email,
            int newsId
            )
        {
            List<Comment> comments = await _repository.GetPaginatedCommentsList(email, newsId, pageNumber, pageSize);

            List<CommentDtoResponse> commentsList = CommentMapper.ToResponseDtoList(comments);

            _paginationBuilder.SetPageNumber(pageNumber);
            _paginationBuilder.SetPageSize(pageSize);
            _paginationBuilder.SetTotalItems(commentsList.Count);
            _paginationBuilder.SetItems(commentsList);

            return _paginationBuilder.Build();
        }

    }
}
