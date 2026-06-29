using SportAppServer.Support2026.Domain.Entities;

namespace SportAppServer.Support2026.Domain.Repositories.NewsRepositoryLayer
{
    public interface INewsRepository: IPaginatedRepository
    {
        Task<List<News>> GetAllNews();
        Task<News?> GetByDateAsync(DateTime dateTime);
        Task AddNewsToDBAsync(List<News> newsList);
        Task<List<News>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        new Task<int> CountItems();
    }
}
