using SportAppServer.Models.DTOs;
using SportAppServer.Models.Pagination;

namespace SportAppServer.Services
{
    public interface INewsService
    {
        Task<List<NewsDTO>> GetAllNews();
        Task<NewsDTO> GetNewsByDateAsync(string dateTime);
        Task<NewsPagination> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
    }
}
