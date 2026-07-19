using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Repositories;

namespace SportAppServer.Support2026.Domain.Feature.NewsFeature.Repository
{
    public interface INewsRepository: IPaginatedRepository
    {
        Task<List<News>> GetAllNews();
        Task<News?> GetByiDAsync(int newsID);
        Task AddNewsToDBAsync(List<News> newsList);
        Task<List<News>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        new Task<int> CountItems();
    }
}
