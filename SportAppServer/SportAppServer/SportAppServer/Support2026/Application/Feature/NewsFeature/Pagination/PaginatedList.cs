namespace SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination
{
    // Represents a paginated result set. Contains metadata such as
    // current page number, page size, total number of
    // items, and the items of the current page.
    public class PaginatedList<T>
    {
        // The number of the current page (starting from 1)
        public int PageNumber { get; set; }


        // The number of items returned on single page. Default is 10
        public int PageSize { get; set; } = 10;


        // The total number of items available in the data source
        public int TotalItems { get; set; }


        // The total number of pages calculated from TotalItems and PageSize.
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }


        // The list of items contained in the current page.
        public List<T> ItemsList { get; set; }
    }
}
