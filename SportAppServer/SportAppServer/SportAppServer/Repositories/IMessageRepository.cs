using SportAppServer.Models.Entities;

namespace SportAppServer.Repositories
{
    public interface IMessageRepository
    {
        Task<Message> AddMessageToDBAsync(Message message);
        Task<List<Message>> GetPaginatedMessageList(string email, int pageNumber = 1, int pageSize = 10);

    }
}
