using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Mappers;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Repository;

namespace SportAppServer.Support2026.Application.Feature.NewsFeature.UseCases
{
    public class GetNewsDetailsUseCase
    {
        private readonly INewsRepository _repository;

        public GetNewsDetailsUseCase(INewsRepository repository)
        {
            _repository = repository;
        }

        public async Task<NewsDetailsDto> Execute(int newsId)
        {

            if (newsId < 1)
            {
                Console.WriteLine("GetNewsDetailsUseCase не верное ID новости" + newsId);
                return null;
            }

            News news = await _repository.GetByiDAsync(newsId);

            if (news != null)
            {
                NewsDetailsDto newsDto = NewsMapper.MapToDtoNewsWithDetails(news);
                
                return newsDto;
            }
            else {
                Console.WriteLine("GetNewsDetailsUseCase: новость не найдена  " + newsId);
                return null;
            }

        }
    }
}
