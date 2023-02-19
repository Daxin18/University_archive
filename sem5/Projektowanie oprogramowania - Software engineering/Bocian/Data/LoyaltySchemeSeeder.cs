using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class LoyaltySchemeSeeder
{
    public static void SeedLoyaltySchemes(this ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<LoyaltyScheme>()
            .HasData(
                new LoyaltyScheme()
                {
                    Id = 1,
                    PointPrice = 2500,
                    StartDate = DateOnly.MinValue,
                    EndDate = DateOnly.MaxValue,
                    ProductId = 1,
                    DiscountPrice = 6.00m
                },
                new LoyaltyScheme()
                {
                    Id = 2,
                    PointPrice = 3500,
                    StartDate = DateOnly.MinValue,
                    EndDate = DateOnly.MaxValue,
                    ProductId = 3,
                    DiscountPrice = 18.00m
                },
                new LoyaltyScheme()
                {
                    Id = 3,
                    PointPrice = 500,
                    StartDate = DateOnly.MinValue,
                    EndDate = DateOnly.FromDateTime(new DateTime(2023, 1, 18)),
                    ProductId = 2,
                    DiscountPrice = 8.00m
                }
            );
    }
}
