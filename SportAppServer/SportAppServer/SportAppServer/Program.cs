using FirebaseAdmin;
using Google.Apis.Auth.OAuth2;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using SportAppServer;
using SportAppServer.Entities.context;
using SportAppServer.Repositories;
using SportAppServer.Services;

internal class Program
{
    private static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);



        builder.Services.AddDbContext<DBContext>(options =>
            options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));


        builder.Services.AddDbContext<DBContext>(options =>
            options.UseSqlServer("Server=Karalenka;Database=KorSport;Trusted_Connection=True;TrustServerCertificate=True"));


        builder.Services.AddScoped<INewsRepository, NewsRepository>();
        builder.Services.AddScoped<INewsService, NewsService>();
        builder.Services.AddScoped<IUserRepository, UserRepository>();
        builder.Services.AddScoped<IUserService, UserService>();



        builder.Services.AddCors(options =>
        {
            options.AddPolicy("AllowAll", builder =>
            {
                builder.AllowAnyOrigin()
                       .AllowAnyMethod()
                       .AllowAnyHeader();
            });
        });



        builder.Services.AddControllers();
        builder.Services.AddRazorPages();

        var app = builder.Build();

        app.UseStaticFiles(new StaticFileOptions
        {
            FileProvider = new Microsoft.Extensions.FileProviders.PhysicalFileProvider("C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\savedImages"),
            RequestPath = "/images"
        });


        app.UseStaticFiles(new StaticFileOptions
        {
            FileProvider = new Microsoft.Extensions.FileProviders.PhysicalFileProvider("C:\\Users\\korol\\AndroidStudioProjects\\SportApp\\SportAppServer\\UsersImages"),
            RequestPath = "/UserImage"
        });



        if (!app.Environment.IsDevelopment())
        {
            app.UseExceptionHandler("/Error");
            app.UseHsts();
        }

        app.UseHttpsRedirection();
        app.UseStaticFiles();

        app.UseRouting();

        app.UseCors("AllowAll");

        app.UseAuthorization();

        app.MapRazorPages();
        app.MapControllers();

        //PythonScript pythonScript = new PythonScript();

        //Timer timer = new Timer(state => pythonScript.RunPythonScriptAsync(), null, TimeSpan.Zero, TimeSpan.FromMinutes(15));

        FirebaseApp.Create(new AppOptions()
        {
            Credential = GoogleCredential.FromFile("C:\\My data\\korsport-ccd21-firebase-adminsdk-fbsvc-5038a58640.json")
        });


        NotificationScheduler notification = new NotificationScheduler();
        notification.Deserialisation();

        notification.CheckNearestDateAndSetNotificationAsync();

        app.Run();
    }
}