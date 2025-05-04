using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using SportAppServer.Context;
using SportAppServer.Entities.Models;
using SportAppServer.Gemini;
using SportAppServer.Models.Entities;
using System.Data;
using System.Diagnostics;


namespace SportAppServer.Repositories
{
    public class NewsRepository: INewsRepository
    {
        private readonly DBContext _context;
        private readonly IGeminiService _gemini;


        public NewsRepository(DBContext context, IGeminiService gemini)
        {
            _context = context;
            _gemini = gemini;
        }

        public async Task<List<News>> GetAllNews()
        {           
            var newsList = await _context.NewsList
                .ToListAsync();

            return newsList;
        }


        public async Task<List<News>> GetPaginatedNewsList(int pageNumber = 1, int pageSize = 10)
        {
            int totalItems = await _context.NewsList.CountAsync();


            var newsList = await _context.NewsList
                .Include(n => n.Tags)
                .OrderByDescending(news => news.DateTime)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToListAsync();

            //TODO  сделать пагинацию на sql сервере


            return newsList;
        }



        public async Task<News?> GetByDateAsync(string dateTime)
        {
            DateTime newsDateTime = DateTime.Parse(dateTime);

            var news = await _context.NewsList
                .Include(n => n.Tags)
                .FirstOrDefaultAsync(item => item.DateTime == newsDateTime);

            return news;
        }




        public async Task AddNewsToDBAsync(List<News> newsList)
        {
            try
            {
                foreach (var newsItem in newsList)
                {
                    var newsFound = await _context.NewsList
                    .Include(n => n.Tags)
                    .FirstOrDefaultAsync(n => n.DateTime == newsItem.DateTime);

                    if (newsFound == null)
                    {
                        if (newsItem.Title != null)
                        {

                            var tags = await _gemini.CreateTags(newsItem.ArticleText);
                            List<NewsTag> emptyTags = new List<NewsTag>();

                            foreach (var item in tags)
                            {
                                var newTag = new NewsTag(item, newsItem.DateTime);
                                await _context.Tags.AddAsync(newTag);
                                emptyTags.Add(newTag);
                            }

                            //newsItem.NewsTags = emptyTags;

                            await _context.NewsList.AddAsync(newsItem);

                            Console.WriteLine($"Новость добавлена: {newsItem.Title}");
                        }
                    }
                    else
                    {
                        Console.WriteLine($"Новость уже существует: {newsItem.Title}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Ошибка при добавлении новостей: {ex.Message}");
            }
        }


        public async Task<int> CountItems()
        {
            return await _context.NewsList.CountAsync();
        }


        public async Task<int> CountComments(DateTime newsDateTime)
        {
            Debug.WriteLine($"CountComments: {newsDateTime}");

            var param = new SqlParameter("@newsDateTime", newsDateTime);
            var outputParam = new SqlParameter("@OutputCount", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            
            await _context.Database.ExecuteSqlRawAsync(
                "EXEC [dbo].[CountComments] @newsDateTime, @OutputCount OUT",
                param, outputParam
            );

            var result = (int)outputParam.Value;

            Debug.WriteLine($"CountComments: {result}");

            return result; 
        }





        public async Task<int> CountLikes(DateTime newsDateTime)
        {
            Debug.WriteLine($"CountLikes: {newsDateTime}");

            var param = new SqlParameter("@newsDateTime", newsDateTime);
            var outputParam = new SqlParameter("@OutputCount", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };


            await _context.Database.ExecuteSqlRawAsync(
                "EXEC [dbo].[LikesCount] @newsDateTime, @OutputCount OUT",
                param, outputParam
            );

            var result = (int)outputParam.Value;

            Debug.WriteLine($"CountLikes: {result}");

            return result;
        }



        public async Task<(List<News>, int totalItems)> GetNewsList(string searchPrompt, int pageSize, int pageNumber, int sportIndex)
        {

            SqlParameter searchParam;

            if (string.IsNullOrEmpty(searchPrompt))
                searchParam = new SqlParameter("@search", DBNull.Value);
            else
                searchParam = new SqlParameter("@search", FormatForFullTextSearch(searchPrompt));

            Debug.WriteLine($"Search Parameter: {searchParam.Value}");

            var outputParam = new SqlParameter("@total", SqlDbType.Int)
            {
                Direction = ParameterDirection.Output
            };

            SqlParameter sportParam;
            if (sportIndex != -1 && sportIndex < Sports.sports.Count)
                sportParam = new SqlParameter("@sport", Sports.sports[sportIndex]);
            else
                sportParam = new SqlParameter("@sport", DBNull.Value);

            var pageNumberParam = new SqlParameter("@PageNumber", pageNumber);
            var pageSizeParam = new SqlParameter("@PageSize", pageSize);

       

            Debug.WriteLine($"Sport Parameter: {sportParam.Value}");

            await _context.Database.ExecuteSqlRawAsync(
                "EXEC CountNews @search, @sport, @total OUT",
                searchParam, sportParam, outputParam
            );

            int totalItems = (int)outputParam.Value;
            Debug.WriteLine($"Total Items: {totalItems}");

            var newsList = await _context.NewsList
                .FromSqlRaw("EXEC SearchNews @search, @sport, @PageNumber, @PageSize",
                    searchParam, sportParam, pageNumberParam, pageSizeParam)
                .ToListAsync();

            foreach (var item in newsList)
            {
                Debug.WriteLine(item);
            }
            Debug.WriteLine("-----------------------------------");

            var paginatedList = newsList
                .OrderByDescending(n => n.DateTime)
                .Skip((pageNumber - 1) * pageSize)
                .Take(pageSize)
                .ToList();

            foreach (var newsItem in newsList)
            {
                newsItem.Tags = await _context.Tags
                    .Where(t => t.NewsDateTime == newsItem.DateTime)
                    .ToListAsync();
            }

            return (newsList, totalItems);
        }




        private string FormatForFullTextSearch(string input)
        {
            var terms = input
                .Split(' ', StringSplitOptions.RemoveEmptyEntries)
                .Select(t => $"\"{t}\"");

            return string.Join(" OR ", terms);
        }

    }
}
