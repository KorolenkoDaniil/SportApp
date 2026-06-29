using SportAppServer.Support2026.Application.Dto;
using SportAppServer.Support2026.Application.Mappers;
using SportAppServer.Support2026.Application.Pagination;
using SportAppServer.Support2026.Domain.Entities;
using SportAppServer.Support2026.Domain.Repositories.NewsRepositoryLayer;

namespace SportAppServer.Support2026.Application.UseCases
{
    public class GetNewsByDateUseCase
    {
        private readonly INewsRepository _repository;
        private IPaginationBuilder<NewsDto> _paginationBuilder;

        public GetNewsByDateUseCase(INewsRepository repository, IPaginationBuilder<NewsDto> paginationBuilder)
        {
            _repository = repository;
            _paginationBuilder = paginationBuilder;
        }

        public async Task<PaginatedList<NewsDto>> Execute(string dateTime)
        {

            if (!DateTime.TryParse(dateTime, out var newsDateTime))
            {
                Console.WriteLine("GetNewsByDateUseCase: неверный формат даты: " + dateTime);
                return null;
            }

            News news = await _repository.GetByDateAsync(newsDateTime);

            if (news != null)
            {
                var newsDtoList = new List<NewsDto> { NewsMapper.MapToDto(news) };

                _paginationBuilder.SetPageNumber(1);
                _paginationBuilder.SetPageSize(1);
                _paginationBuilder.SetTotalItems(newsDtoList.Count);
                _paginationBuilder.SetItems(newsDtoList);

                return _paginationBuilder.Build();
            }
            else {
                Console.WriteLine("GetNewsByDateUseCase: новость не найдена  " + dateTime);
                return null;
            }

        }
    }
}
