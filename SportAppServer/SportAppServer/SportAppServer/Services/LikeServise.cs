using SportAppServer.Models.Entities;
using SportAppServer.Models.Mappers;
using SportAppServer.Repositories;

namespace SportAppServer.Services
{
    public class LikeServise : ILikeServise
    {
        private readonly ILikeRepository _likeRepository;

        public  LikeServise(ILikeRepository likeRepository)
        {
            _likeRepository = likeRepository;
        }

        public Task<int> AddLikeAsync(LikeDto like)
        {
            return _likeRepository.AddLikeAsync(LikeMapper.ConvertToEntity(like));
        }

    
        public Task<int> RemoveLikeAsync(LikeDto like)
        {
            return _likeRepository.RemoveLikeAsync(like.NewsDateTime, like.UserEmail);
        }
    }
}
