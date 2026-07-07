
using Newtonsoft.Json;

namespace SportAppServer.Support2026.Application.Pagination
{
    // Represents a paginated result set. Contains metadata such as
    // current page number, page size, total number of
    // items, and the items of the current page.
    public class PaginatedList<T>
    {
        // The number of the current page (starting from 1)
        [JsonProperty("page_number")]
        public int PageNumber { get; set; }


        // The number of items returned on single page. Default is 10
        [JsonProperty("page_size")]
        public int PageSize { get; set; } = 10;


        // The total number of items available in the data source
        [JsonProperty("total_items")]
        public int TotalItems { get; set; }


        // The total number of pages calculated from TotalItems and PageSize.
        [JsonProperty("total_pages")]
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }


        // The list of items contained in the current page.
        [JsonProperty("items_list")]
        public List<T> ItemsList { get; set; }
    }
}
