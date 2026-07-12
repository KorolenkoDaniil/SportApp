using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Mappers;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Repositories.NewsRepositoryLayer;

namespace SportAppServer.Support2026.Application.Feature.NewsFeature.UseCases
{
    public class GetNewsByIdUseCase
    {
        private readonly INewsRepository _repository;
        private IPaginationBuilder<NewsApiDto> _paginationBuilder;

        public GetNewsByIdUseCase(INewsRepository repository, IPaginationBuilder<NewsApiDto> paginationBuilder)
        {
            _repository = repository;
            _paginationBuilder = paginationBuilder;
        }

        public async Task<PaginatedList<NewsApiDto>> Execute(int newsId)
        {

            if (newsId < 1)
            {
                Console.WriteLine("GetNewsByIdUseCase не верное ID новости" + newsId);
                return null;
            }

            News news = await _repository.GetByiDAsync(newsId);

            if (news != null)
            {
                var newsDtoList = new List<NewsApiDto> { NewsMapper.MapToDto(news) };

                _paginationBuilder.SetPageNumber(1);
                _paginationBuilder.SetPageSize(1);
                _paginationBuilder.SetTotalItems(newsDtoList.Count);
                _paginationBuilder.SetItems(newsDtoList);

                return _paginationBuilder.Build();
            }
            else {
                Console.WriteLine("GetNewsByIdUseCase: новость не найдена  " + newsId);
                return null;
            }

        }
    }
}
