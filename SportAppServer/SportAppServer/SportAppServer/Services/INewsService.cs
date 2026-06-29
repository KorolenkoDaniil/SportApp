using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Application.Pagination;

namespace SportAppServer.Services
{
    public interface INewsService
    {
        Task<List<NewsDto>> GetAllNews();
        Task<NewsDto> GetNewsByDateAsync(string dateTime, string userEmail);
        Task<PaginatedList<NewsDto>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        //Task<NewsPagination> GetPaginatedNewsListwithSearch(string searchPrompt, int pageNumber, int pageSize, int sportIndex);
    }
}
