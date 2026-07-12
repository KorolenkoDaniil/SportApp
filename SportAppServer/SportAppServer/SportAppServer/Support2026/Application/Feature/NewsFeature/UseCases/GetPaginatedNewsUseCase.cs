using SportAppServer.Support2026.Application.Feature.NewsFeature.Dto;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Mappers;
using SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Entities;
using SportAppServer.Support2026.Domain.Feature.NewsFeature.Repositories.NewsRepositoryLayer;

namespace SportAppServer.Support2026.Application.Feature.NewsFeature.UseCases
{
    public class GetPaginatedNewsUseCase
    {
        private readonly INewsRepository _repository;
        private PaginationBuilder<NewsApiDto> _paginationBuilder;

        public GetPaginatedNewsUseCase(INewsRepository repository, IPaginationBuilder<NewsApiDto> paginationBuilder)
        {
            _repository = repository;
            _paginationBuilder = (PaginationBuilder<NewsApiDto>)paginationBuilder;
        }

        public async Task<PaginatedList<NewsApiDto>> Execute(int pageNumber, int pageSize)
        {
           
            List<News> newsList = await _repository.GetPaginatedNewsList(pageNumber, pageSize);

            List<NewsApiDto> newsDtoList = newsList.Select(NewsMapper.MapToApiDto).ToList();

            _paginationBuilder.SetPageNumber(pageNumber);
            _paginationBuilder.SetPageSize(pageSize);
            _paginationBuilder.SetTotalItems(newsDtoList.Count);
            _paginationBuilder.SetItems(newsDtoList);

            return _paginationBuilder.Build();

        }


      
    }
}
