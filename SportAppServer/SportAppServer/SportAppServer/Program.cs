using Microsoft.EntityFrameworkCore;
using SportAppServer;
using SportAppServer.Entities.context;

var builder = WebApplication.CreateBuilder(args);



builder.Services.AddDbContext<NewsContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection"))); 

builder.Services.AddControllers();




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

app.Run();

