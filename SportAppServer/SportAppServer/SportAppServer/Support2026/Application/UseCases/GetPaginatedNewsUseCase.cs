using SportAppServer.Models.Pagination;
using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Application.Mappers;
using SportAppServer.Support2026.Domain.Entities;
using SportAppServer.Support2026.Domain.Repositories.NewsRepositoryLayer;

namespace SportAppServer.Support2026.Application.UseCases
{
    public class GetPaginatedNewsUseCase
    {
        private readonly INewsRepository _repository;

        public GetPaginatedNewsUseCase(INewsRepository repo)
        {
            _repository = repo;
        }

        public async Task<NewsPagination> Execute(int pageNumber, int pageSize)
        {
            List<News> newsList = await _repository.GetPaginatedNewsList(pageNumber, pageSize);

            List<NewsDto> newsDtoList = newsList.Select(NewsMapper.MapToDto).ToList();

            return await MapToPaginatedList(pageNumber, pageSize, newsDtoList);

        }


        private async Task<NewsPagination> MapToPaginatedList (int pageNumber, int pageSize, List<NewsDto> newsDtos)
        {
            return new NewsPagination
            {
                PageNumber = pageNumber,
                PageSize = pageSize,
                TotalItems = await _repository.CountItems(),
                News = newsDtos
            };
        }
    }
}
