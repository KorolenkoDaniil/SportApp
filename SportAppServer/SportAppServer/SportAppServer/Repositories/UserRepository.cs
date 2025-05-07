using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using SportAppServer.Context;
using SportAppServer.Models.Entities;
using System.Data;
using System.Diagnostics;

namespace SportAppServer.Repositories
{
    public class UserRepository : IUserRepository
    {
        private readonly DBContext _context;

        public UserRepository(DBContext context)
        {
            _context = context;
        }

        public async Task<User> GetUserData(string email)
        {

            var user = await _context.Users
    .           FirstOrDefaultAsync(u => u.UserEmail == email);


            Debug.WriteLine(user + "---------------------------------------11111111111111111111111111");

            return user;
        }

        public async Task<User> PutUser(string email)
        {
            List<Comment> comments = new List<Comment>();
            List<Like> likes = new List<Like>();

            var newUser = new User(email, "0y3wav6f03b2m9vup3yunrdm3u3rnm4s.jpg", comments);

            _context.Users.Add(newUser);

            await _context.SaveChangesAsync();

            return newUser;
        }


        public async Task<string> PutUserImage(string email, IFormFile image)
        {
            SqlParameter emailParam;
            
                emailParam = new SqlParameter("@email", email);

            var user = _context.Users
                .FromSqlRaw("EXEC SearchUserByEmail @email", emailParam)
                .AsEnumerable()
                .FirstOrDefault();


            //Метод.AsEnumerable() в данном контексте используется для принудительного выполнения 
            //SQL-запроса и перевода результата из EF Core IQueryable в LINQ на уровне C#, чтобы 
            //можно было безопасно использовать методы, не поддерживаемые SQL-сервером — такие как 
            //.FirstOrDefault() в чистом C#.



            if (user == null)
            {
                return "";
            }


            string extension = Path.GetExtension(image.FileName);
            string fileName = $"{Guid.NewGuid()}{extension}";

            user.UserImage = fileName;

            _context.Attach(user);
            _context.Entry(user).Property(u => u.UserImage).IsModified = true;

            string filePath = Path.Combine("C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\UsersImages", fileName);


            using (var stream = new FileStream(filePath, FileMode.Create))
            {
                await image.CopyToAsync(stream);
            }

            await _context.SaveChangesAsync();

            return fileName;
        }
    }
}


