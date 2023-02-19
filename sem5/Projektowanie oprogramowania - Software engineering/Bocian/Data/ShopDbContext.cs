using Bocian.Models;
using Microsoft.EntityFrameworkCore;
using Bocian.ViewModels;

namespace Bocian.Data;

public class ShopDbContext : DbContext
{
    public ShopDbContext(DbContextOptions<ShopDbContext> options) : base(options) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Account>().UseTptMappingStrategy();

        modelBuilder.SeedAddresses();
        modelBuilder.SeedShops();
		modelBuilder.SeedAccounts();
		modelBuilder.SeedProducts();
        modelBuilder.SeedOrders();
        modelBuilder.SeedLoyaltySchemes();
    }

    public DbSet<Account> Accounts => Set<Account>();
    public DbSet<Address> Addresses => Set<Address>();
    public DbSet<Cart> Carts => Set<Cart>();
    public DbSet<CartItem> CartItems => Set<CartItem>();
    public DbSet<Category> Categories => Set<Category>();
    public DbSet<Client> Clients => Set<Client>();
    public DbSet<Complaint> Complaints => Set<Complaint>();
    public DbSet<Courier> Couriers => Set<Courier>();
    public DbSet<Delivery> Deliveries => Set<Delivery>();
    public DbSet<Employee> Employees => Set<Employee>();
    public DbSet<FavoriteProduct> FavoriteProducts => Set<FavoriteProduct>();
    public DbSet<Image> Images => Set<Image>();
    public DbSet<LoyaltyScheme> LoyaltySchemes => Set<LoyaltyScheme>();
    public DbSet<Manager> Managers => Set<Manager>();
    public DbSet<Order> Orders => Set<Order>();
    public DbSet<Payment> Payments => Set<Payment>();
    public DbSet<Product> Products => Set<Product>();
    public DbSet<Shop> Shops => Set<Shop>();
    public DbSet<TransportationType> TransportationTypes => Set<TransportationType>();
    public DbSet<ProductCount> ProductCount => Set<ProductCount>();
}
