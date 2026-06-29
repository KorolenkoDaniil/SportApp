namespace SportAppServer.Support2026.Application.Pagination
{
    public class PaginationBuilder<T> : IPaginationBuilder<T>
    {
        private int _pageSize;
        private int _pageNumber;
        private int _totalItmes;
        private List<T> _items;

        public PaginatedList<T> Build()
        {
            return new PaginatedList<T>
            {
                PageNumber = _pageNumber,
                PageSize = _pageSize,
                TotalItems = _totalItmes,
                ItemsList = _items
            };
        }

        public void SetItems(List<T> items)
        {
            _items = items;
        }

        public void SetPageNumber(int pageNumber)
        {
            _pageNumber = pageNumber;
        }

        public void SetPageSize(int pageSize)
        {
            _pageSize = pageSize;
        }

        public void SetTotalItems(int totalItems)
        {
            _totalItmes = totalItems;
        }
    }
}
