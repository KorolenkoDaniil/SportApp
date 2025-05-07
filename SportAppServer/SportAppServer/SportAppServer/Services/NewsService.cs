using Microsoft.Extensions.Caching.Distributed;
using Newtonsoft.Json;
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
        private readonly IDistributedCache _distributedCache;

        public NewsService(INewsRepository newsRepository, ILikeRepository likeRepository, IDistributedCache distributedCache)
        {
            _newsRepository = newsRepository;
            _likeRepository = likeRepository;
            _distributedCache = distributedCache;
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

        public async Task<NewsPagination> GetPaginatedNewsList(int pageNumber, int pageSize)
        {
            List<News> newsList = new List<News>();

            string cachedNews = await _distributedCache.GetStringAsync("cachedNewsList");

      

            if (cachedNews != null)
            {
                var newsListCached = JsonConvert.DeserializeObject<List<News>>(cachedNews);

                int skip = (pageNumber - 1) * pageSize;

                if (skip + pageSize <= newsListCached.Count)
                {
                    newsList = newsListCached
                    .Skip((pageNumber - 1) * pageSize)
                    .Take(pageSize)
                    .ToList();

                    newsList = await _newsRepository.GetTags(newsList);
                } 
            }
            else
            {
                newsList = await _newsRepository.GetPaginatedNewsList(pageNumber, pageSize);

                foreach (var item in newsList)
                {
                    Debug.WriteLine(item.ToString());
                }

            }
           
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

        public async Task<NewsPagination> GetPaginatedNewsListwithSearch(string searchPrompt, int pageSize, int pageNumber, int sportIndex)
        {
            if (!string.IsNullOrEmpty(searchPrompt))
            {
                searchPrompt = searchPrompt.ToLower().Trim();
                searchPrompt = await LemmatizeService.GetLems(searchPrompt);
            }

            Debug.WriteLine(searchPrompt);


            List<News> newsList;
            int totalItems;
           
            (newsList, totalItems) = await _newsRepository.GetNewsList(searchPrompt, pageSize, pageNumber, sportIndex);
            
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
