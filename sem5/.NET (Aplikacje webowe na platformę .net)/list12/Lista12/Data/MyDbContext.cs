using Microsoft.EntityFrameworkCore;
using Lista12.Models;

namespace Lista12.Data
{
    public class MyDbContext : DbContext
    {

        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options)
        {
        }
        public DbSet<Category> Category { get; set; }
        public DbSet<Article> Article { get; set; }
    }
}
