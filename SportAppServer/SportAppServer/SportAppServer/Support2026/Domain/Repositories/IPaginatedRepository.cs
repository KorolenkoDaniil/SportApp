namespace SportAppServer.Support2026.Domain.Repositories
{
    public interface IPaginatedRepository
    {
        Task<int> CountItems();
    }
}
