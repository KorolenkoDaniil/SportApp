namespace SportAppServer.Support2026.Domain.Feature.NewsFeature.Repositories
{
    public interface IPaginatedRepository
    {
        Task<int> CountItems();
    }
}
