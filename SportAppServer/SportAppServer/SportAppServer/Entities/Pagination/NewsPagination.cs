using SportAppServer.Entities.models;

namespace SportAppServer.Entities.Pagination
{
    public class NewsPagination
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; } = 20;
        public int TotalItems { get; set; }
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }
        public List<News> News { get; set; }
    }


}
