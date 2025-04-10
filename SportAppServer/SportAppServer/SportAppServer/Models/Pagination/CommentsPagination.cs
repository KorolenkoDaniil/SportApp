using SportAppServer.Models.Entities;

namespace SportAppServer.Models.Pagination
{
    public class CommentsPagination
    {
        public int PageNumber { get; set; }
        public int PageSize { get; set; } = 20;
        public int TotalItems { get; set; }
        public int TotalPages
        {
            get { return (int)Math.Ceiling((decimal)TotalItems / PageSize); }
        }
        public required List<CommentDTO> Comments { get; set; }
    }


}
