using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination;

namespace SportAppServer.Support2026.Domain.Feature.NewsFeature.Services
{
    public interface INewsService
    {
        Task<List<NewsApiDto>> GetAllNews();
        Task<NewsApiDto> GetNewsByDateAsync(string dateTime, string userEmail);
        Task<PaginatedList<NewsApiDto>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        //Task<NewsPagination> GetPaginatedNewsListwithSearch(string searchPrompt, int pageNumber, int pageSize, int sportIndex);
    }
}
