using Microsoft.EntityFrameworkCore;
using Lista10.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;

namespace Lista10.Data
{
    public class MyDbContext : IdentityDbContext
    {

        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options)
        {
        }
        public DbSet<Category> Category { get; set; }
        public DbSet<Article> Article { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder); //create tables for identity
        }
    }
}
