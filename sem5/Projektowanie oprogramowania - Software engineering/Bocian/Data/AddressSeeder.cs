using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class AddressSeeder
{
    public static void SeedAddresses(this ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<Address>()
            .HasData(
                new Address
                {
                    Id = 1,
                    PostalCode = "58-100",
                    City = "Wrocław",
                    Street = "Słowackiego",
                    HouseNumber = 18,
                    ApartmentNumber = 5,
                },
                new Address
                {
                    Id = 2,
                    PostalCode = "58-123",
                    City = "Warszawa",
                    Street = "Fajna",
                    HouseNumber = 13,
                    ApartmentNumber = null,
                },
                new Address
                {
                    Id = 3,
                    PostalCode = "57-420",
                    City = "Opole",
                    Street = "Słoneczna",
                    HouseNumber = 18,
                    ApartmentNumber = 1,
                },
                new Address
                {
                    Id = 4,
                    PostalCode = "47-100",
                    City = "Kraków",
                    Street = "Stara",
                    HouseNumber = 1,
                    ApartmentNumber = null,
                },
                new Address
                {
                    Id = 5,
                    PostalCode = "58-333",
                    City = "Warszawa",
                    Street = "Szeroka",
                    HouseNumber = 123,
                    ApartmentNumber = 7,
                },
                new Address
                {
                    Id = 1001,
                    PostalCode = "50-438",
                    City = "Wrocław",
                    Street = "Tadeusza Kościuszki",
                    HouseNumber = 198,
                    ApartmentNumber = 1
                },
                new Address
                {
                    Id = 1002,
                    PostalCode = "50-420",
                    City = "Wrocław",
                    Street = "Generała Romualda Traugutta",
                    HouseNumber = 98,
                    ApartmentNumber = 100
                },
                new Address
                {
                    Id = 1003,
                    PostalCode = "50-452",
                    City = "Wrocław",
                    Street = "Komuny Paryskiej",
                    HouseNumber = 83,
                    ApartmentNumber = null
                },
                new Address
                {
                    Id = 1004,
                    PostalCode = "50-413",
                    City = "Wrocław",
                    Street = "Walońska",
                    HouseNumber = 17,
                    ApartmentNumber = 1
                }
            );
    }
}
