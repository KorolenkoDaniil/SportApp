namespace SportAppServer.Support2026.Application.Pagination
{

    // Pagination builder interface used to construct a
    // PaginatedList<T> with metadata such as page number,
    // page size, total items, and the items of the current page.
    public interface IPaginationBuilder<T>
    {
        // Sets the number of the page to return (starting from 1).
        void SetPageNumber(int pageNumber);


        // Sets how many items should be included on a single page.
        void SetPageSize(int pageSize);


        // Sets the total number of items available in the data source,
        // not the number of items on the current page.
        void SetTotalItems(int totalItems);


        // Sets the list of items that belong to the current page.
        void SetItems(List<T> items);


        // Builds and returns a PaginatedList<T> using the provided pagination parameters.
        PaginatedList<T> Build();
    }
}
