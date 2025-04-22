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
        Task<int> CountComments(DateTime newsDateTime);
        Task<int> CountLikes(DateTime newsDateTime);
        Task<(List<News>, int totalItems)> GetNewsListwithSearch(string searchPrompt, int pageNumber = 1, int pageSize = 10);


    }
}
