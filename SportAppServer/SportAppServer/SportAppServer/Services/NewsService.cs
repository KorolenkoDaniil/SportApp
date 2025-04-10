using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;

namespace SportAppServer.Services
{
    public class NewsService : INewsService
    {

        private readonly INewsRepository _newsRepository;

        public NewsService(INewsRepository newsRepository)
        {
            _newsRepository = newsRepository;
        }

        public Task<string> GetAllNews()
        {
            //var page = new NewsPagination
            //{
            //    PageNumber = pageNumber,
            //    PageSize = pageSize,
            //    TotalItems = totalItems,
            //    News = newsList
            //};

            //string json = JsonConvert.SerializeObject(page, new JsonSerializerSettings
            //{
            //    Formatting = Formatting.Indented,
            //    ReferenceLoopHandling = ReferenceLoopHandling.Ignore
            //});

            //return Content(json, "application/json");
            throw new NotImplementedException();
        }

        public async Task<NewsDTO> GetNewsByDateAsync(string dateTime)
        {
            var searchResult = await _newsRepository.GetByDateAsync(dateTime);


            if (searchResult != null)
            {
                var newsDto = NewsMapper.ConvertToDTO(searchResult);
                return newsDto;
            }

            return null;

        }

        public async Task<NewsPagination> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10)
        {
            List<News> newsList = await _newsRepository.GetPaginatedNewsList(pageNumber, pageSize);

            int totalItems = await _newsRepository.CountItems();

            var page = new NewsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                News = NewsMapper.ConvertToListOfDTO(newsList)
            };

            return page;

        }

        Task<List<NewsDTO>> INewsService.GetAllNews()
        {
            throw new NotImplementedException();
        }
    }
}
