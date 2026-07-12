namespace SportAppServer.Support2026.Application.Feature.NewsFeature.Pagination
{
    // Implementation of pattern Builder for pagintion
    public class PaginationBuilder<T> : IPaginationBuilder<T>
    {
        private int _pageSize;
        private int _pageNumber;
        private int _totalItems;
        private List<T> _items;

        public PaginatedList<T> Build()
        {
            return new PaginatedList<T>
            {
                PageNumber = _pageNumber,
                PageSize = _pageSize,
                TotalItems = _totalItems,
                ItemsList = _items
            };
        }


        // Sets the number of the page to return (starting from 1).
        public void SetItems(List<T> items)
        {
            _items = items;
        }

        public void SetPageNumber(int pageNumber)
        {
            _pageNumber = pageNumber;

            //TODO сделать проверку на число
        }

        public void SetPageSize(int pageSize)
        {
            _pageSize = pageSize;

            //TODO сделать проверку на число
        }

        public void SetTotalItems(int totalItems)
        {
            _totalItems = totalItems;

            //TODO сделать проверку на число
        }
    }
}
