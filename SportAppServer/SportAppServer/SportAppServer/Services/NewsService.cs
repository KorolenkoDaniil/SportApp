using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Models.Pagination;
using SportAppServer.Repositories;
using SportAppServer.Services.LemmatizeMicroService;
using System.Diagnostics;

namespace SportAppServer.Services
{
    public class NewsService : INewsService
    {

        private readonly INewsRepository _newsRepository;
        private readonly ILikeRepository _likeRepository;

        public NewsService(INewsRepository newsRepository, ILikeRepository likeRepository)
        {
            _newsRepository = newsRepository;
            _likeRepository = likeRepository;
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

        public async Task<NewsDTO> GetNewsByDateAsync(string dateTime, string userEmail)
        {
            var searchResult = await _newsRepository.GetByDateAsync(dateTime);


            if (searchResult != null)
            {
                NewsDTO newsDto = NewsMapper.ConvertToDTO(searchResult);

                newsDto.Comments_count = await _newsRepository.CountComments(newsDto.DateTime);
                newsDto.Likes_count = await _newsRepository.CountLikes(newsDto.DateTime);
                

                newsDto.Is_Liked = await LikeExist(newsDto.DateTime, userEmail);

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

        public async Task<NewsPagination> GetPaginatedNewsListwithSearch(string searchPrompt, int pageNumber = 1, int pageSize = 10)
        {

            searchPrompt = searchPrompt.ToLower().Trim();

            searchPrompt = await LemmatizeService.GetLems(searchPrompt);

            Debug.WriteLine(searchPrompt);


            var (newsList, totalItems) = await _newsRepository.GetNewsListwithSearch(searchPrompt, pageNumber, pageSize);


            var page = new NewsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = totalItems,
                News = NewsMapper.ConvertToListOfDTO(newsList)
            };

            return page;
        }





        private Task<bool> LikeExist(DateTime newsDateTime, string email)
        {
            Debug.WriteLine(newsDateTime.ToString());
            Debug.WriteLine(email);
            return _likeRepository.LikeExist(newsDateTime, email);
        }

        Task<List<NewsDTO>> INewsService.GetAllNews()
        {
            throw new NotImplementedException();
        }


       
    }
}
