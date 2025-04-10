using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface INewsRepository
    {
        Task<List<News>> GetAllNews();
        Task<News?> GetByDateAsync(string dateTime);
        Task AddNewsToDBAsync(List<News> newsList);
        Task<List<News>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        Task<int> CountItems();
    }
}
