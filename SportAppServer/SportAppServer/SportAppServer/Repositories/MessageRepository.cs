//using Microsoft.Data.SqlClient;
//using Microsoft.EntityFrameworkCore;
//using SportAppServer.Context;
//using SportAppServer.Models.Entities;


//namespace SportAppServer.Repositories
//{
//    public class MessageRepository : IMessageRepository
//    {
//        private readonly ApplicationDbContext _context;


//        public MessageRepository(ApplicationDbContext context)
//        {
//            _context = context;
//        }

      
//        public async Task<List<Message>> GetPaginatedMessageList (string email, int pageNumber = 1, int pageSize = 10)
//        {
//            var pageNumberParam = new SqlParameter("@PageNumber", pageNumber);
//            var pageSizeParam = new SqlParameter("@PageSize", pageSize);
//            var emailParam = new SqlParameter("@PageSize", email);

//            var messagesList = await _context.AIMessages
//                .FromSqlRaw("EXEC TakePaginatedMessages @PageNumber, @PageSize, @Email", pageNumberParam, pageSizeParam, emailParam)
//                .ToListAsync();

//            return messagesList;
//        }

//        public async Task<Message> AddMessageToDBAsync(Message message)
//        {
//            try
//            {
//                var entry = await _context.AIMessages.AddAsync(message);
//                await _context.SaveChangesAsync();
//                return entry.Entity;
//            }
//            catch (Exception ex)
//            {
//                Console.WriteLine($"Ошибка при добавлении сообщения: {ex.Message}");
//                return null;
//            }
//        }



//        public async Task<int> CountItems()
//        {
//            return await _context.NewsList.CountAsync();
//        }
//    }
//}
