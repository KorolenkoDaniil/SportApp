using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Pagination;

namespace SportAppServer.Services
{
    public interface INewsService
    {
        Task<List<NewsDTO>> GetAllNews();
        Task<NewsDTO> GetNewsByDateAsync(string dateTime, string userEmail);
        Task<NewsPagination> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10);
        //Task<bool> LikeExist(DateTime newsDateTime, string email);
    }
}
