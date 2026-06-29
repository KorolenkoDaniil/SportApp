namespace SportAppServer.Support2026.Application.Pagination
{
    public interface IPaginationBuilder<T>
    {
        void SetPageNumber(int pageNumber);
        void SetPageSize(int pageSize);
        void SetTotalItems(int totalItems);
        void SetItems(List<T> items);

        PaginatedList<T> Build();
    }
}
