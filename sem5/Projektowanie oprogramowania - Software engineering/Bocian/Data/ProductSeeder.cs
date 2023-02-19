using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class ProductSeeder
{
    public static void SeedProducts(this ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<Category>()
            .HasData(
                new Category
                {
                    Id = 1,
                    Name = "Fast food"
                },
                new Category
                {
                    Id = 2,
                    Name = "Nabiał"
                }
            );

        modelBuilder
            .Entity<Image>()
            .HasData(
                new Image
                {
                    Id = 1,
                    Url =
                        "https://upload.wikimedia.org/wikipedia/commons/c/c0/Pizza_with_tomatoes.jpg"
                },
                new Image
                {
                    Id = 2,
                    Url =
                        "https://upload.wikimedia.org/wikipedia/commons/2/28/Polish_%22Zapiekanka%22.jpg"
                },
                new Image
                {
                    Id = 3,
                    Url =
                        "https://s3.party.pl/newsy/zolty-ser-307663-16_9.jpg"
                },
                new Image
                {
                    Id = 4,
                    Url =
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Milk_glass.jpg/330px-Milk_glass.jpg"
                }
            );

        modelBuilder
            .Entity<Product>()
            .HasData(
                new Product
                {
                    Id = 1,
                    Name = "Pizza",
                    Description = "Potrawa kuchni włoskiej, obecnie szeroko rozpowszechniona na całym świecie.",
                    Price = 13.00m,
                    OnlyForAdults = false,
                    Weight = 0.300,
                    CategoryId = 1,
                    ImageId = 1
                },
                new Product
                {
                    Id = 2,
                    Name = "Zapiekanka",
                    Description = "Polski fast food, powstały w latach 70. XX wieku, w okresie Polskiej Rzeczypospolitej Ludowej.",
                    Price = 7.00m,
                    OnlyForAdults = false,
                    Weight = 0.230,
                    CategoryId = 1,
                    ImageId = 2
                },
                new Product
                {
                    Id = 3,
                    Name = "Ser",
                    Description = "Produkt spożywczy wytwarzany poprzez wytrącenie z mleka tłuszczu i białka w postaci skrzepu, który zostaje poddany dalszej obróbce.",
                    Price = 23.00m,
                    OnlyForAdults = false,
                    Weight = 1.000,
                    CategoryId = 2,
                    ImageId = 3
                },
                new Product
                {
                    Id = 4,
                    Name = "Mleko",
                    Description = "Mleko ma liczne wartości odżywcze - w jego skład wchodzą witaminy i pierwiastki mineralne, odpowiedzialne za prawidłowe funkcjonowanie organizmu.",
                    Price = 1.50m,
                    OnlyForAdults = false,
                    Weight = 1.003,
                    CategoryId = 2,
                    ImageId = 4
                }
            );

        modelBuilder
            .Entity<ProductCount>()
            .HasData(
                new ProductCount
                {
                    Id = 1,
                    ProductId = 1,
                    ShopId = 1001,
                    Quantity = 55
                },
                new ProductCount
                {
                    Id = 2,
                    ProductId = 2,
                    ShopId = 1001,
                    Quantity = 0
                }
                ,
                new ProductCount
                {
	                Id = 3,
	                ProductId = 3,
	                ShopId = 1001,
	                Quantity = 215
                }
                ,
                new ProductCount
                {
	                Id = 4,
	                ProductId = 4,
	                ShopId = 1001,
	                Quantity = 1
                }
			);
    }
}
