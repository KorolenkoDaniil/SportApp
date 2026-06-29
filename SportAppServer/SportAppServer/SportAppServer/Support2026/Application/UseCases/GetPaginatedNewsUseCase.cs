using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Application.Mappers;
using SportAppServer.Support2026.Application.Pagination;
using SportAppServer.Support2026.Domain.Entities;
using SportAppServer.Support2026.Domain.Repositories.NewsRepositoryLayer;

namespace SportAppServer.Support2026.Application.UseCases
{
    public class GetPaginatedNewsUseCase
    {
        private readonly INewsRepository _repository;
        private PaginationBuilder<NewsDto> _paginationBuilder;

        public GetPaginatedNewsUseCase(INewsRepository repository, IPaginationBuilder<NewsDto> paginationBuilder)
        {
            _repository = repository;
            _paginationBuilder = (PaginationBuilder<NewsDto>)paginationBuilder;
        }

        public async Task<PaginatedList<NewsDto>> Execute(int pageNumber, int pageSize)
        {
           
            List<News> newsList = await _repository.GetPaginatedNewsList(pageNumber, pageSize);

            List<NewsDto> newsDtoList = newsList.Select(NewsMapper.MapToDto).ToList();

            _paginationBuilder.SetPageNumber(pageNumber);
            _paginationBuilder.SetPageSize(pageSize);
            _paginationBuilder.SetTotalItems(newsDtoList.Count);
            _paginationBuilder.SetItems(newsDtoList);

            return _paginationBuilder.Build();

        }


      
    }
}
