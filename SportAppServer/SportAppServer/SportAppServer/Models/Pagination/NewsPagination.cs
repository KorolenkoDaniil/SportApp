using SportAppServer.Support2026.Application.Dto;

namespace SportAppServer.Models.Pagination
{
    public class NewsPagination
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; } = 10;
        public int TotalItems { get; set; }
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }
        public List<NewsDto> News { get; set; }
    }


}
