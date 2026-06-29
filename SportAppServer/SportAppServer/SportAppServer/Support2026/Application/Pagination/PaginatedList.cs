
namespace SportAppServer.Support2026.Application.Pagination
{
    public class PaginatedList<T>
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; } = 10;
        public int TotalItems { get; set; }
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }
        public List<T> ItemsList { get; set; }
    }
}
