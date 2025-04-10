using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;
using System.Diagnostics;

namespace SportAppServer.Services
{
    public class CommentsService: ICommentsService

    {
        private readonly ICommentsRepository _commentRepository;

        public CommentsService(ICommentsRepository newsRepository)
        {
            _commentRepository = newsRepository;
        }

        public async Task<CommentsPagination> GetPaginatedCommentsList(DateTime itemId, int pageNumber = 1, int pageSize = 10)
        {
            List<Comment> commmentsList = await _commentRepository.GetPaginatedCommentsList(itemId, pageNumber, pageSize);

            int totalItems = await _commentRepository.CountItems(itemId);

            var page = new CommentsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                Comments  = CommentMapper.ConvertToListOfDTO(commmentsList)
            };

            foreach (var item in page.Comments)
            {
                Debug.WriteLine(item);
            }

            Debug.WriteLine("-------------------------");

            return page;
        }
    }
}
