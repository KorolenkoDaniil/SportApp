using SportAppServer.Entities.Models;
using SportAppServer.Models.DTOs;
using SportAppServer.Models.Entities;

namespace SportAppServer.Models.Pagination
{
    public class NewsPagination
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; } = 5;
        public int TotalItems { get; set; }
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }
        public List<NewsDTO> News { get; set; }
    }


}
