using Bocian.Common.Exceptions;
using Bocian.Data;
using Bocian.Services;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Authentication.Cookies;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddScoped<IAuthService, AuthService>();
builder.Services.AddScoped<IOrderService, OrderService>();
builder.Services.AddScoped<IShopService, ShopService>();
builder.Services.AddScoped<IManagerOrderService, ManagerOrderService>();
builder.Services.AddScoped<IManagerProductService, ManagerProductService>();

builder.Services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());
builder.Services.AddControllersWithViews(
    options => options.Filters.Add(new ExceptionInterceptor())
);
builder.Services
    .AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
    .AddCookie(options =>
    {
        options.LoginPath = "/account/login";
        options.LogoutPath = "/account/logout";
        options.AccessDeniedPath = "/forbidden";
    });
builder.Services.AddDbContext<ShopDbContext>(
    options =>
        options
            .UseNpgsql(builder.Configuration.GetConnectionString("ShopDb"))
            .UseAllCheckConstraints()
);

var app = builder.Build();

if (app.Environment.IsDevelopment())
    app.UseDeveloperExceptionPage();
else
    app.UseHsts();

app.UseStatusCodePages();
app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapControllers();
app.MapFallbackToController("ErrorNotFound", "Home");

app.Run();
