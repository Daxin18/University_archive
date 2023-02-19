using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class AccountSeeder
{
    public static void SeedAccounts(this ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<TransportationType>()
            .HasData(new TransportationType { Id = 1, Name = "Skuter" });

        modelBuilder
            .Entity<Employee>()
            .HasData(
                new Employee
                {
                    Id = 1,
                    Email = "pracownik@ebocian.pl",
                    FirstName = "Tomasz",
                    LastName = "Chojnacki",
                    ShopId = 1
                },
                new Employee
                {
                    Id = 1001,
                    Email = "pracownikSlup@ebocian.pl",
                    FirstName = "Pracownik",
                    LastName = "Slup",
                    ShopId = 1001
                }
            );

        modelBuilder
            .Entity<Courier>()
            .HasData(
                new Courier
                {
                    Id = 2,
                    Email = "kurier@ebocian.pl",
                    FirstName = "Tomasz",
                    LastName = "Chojnacki",
                    AvailableFrom = new TimeOnly(8, 00),
                    AvailableTo = new TimeOnly(15, 00),
                    TransportationTypeId = 1
                }
            );

        modelBuilder
            .Entity<Manager>()
            .HasData(
                new Manager
                {
                    Id = 3,
                    Email = "kierownik@ebocian.pl",
                    FirstName = "Jakub",
                    LastName = "Zehner",
                    ShopId = 1
                }
            );

        modelBuilder
            .Entity<Client>()
            .HasData(
                new Client
                {
                    Id = 4,
                    Email = "klient@ebocian.pl",
                    FirstName = "Kamil",
                    LastName = "Ciągło",
                    PhoneNumber = "+48 123 456 789",
                    OwnedPoints = 3000,
                    SpentPoints = 0
                },
                new Client
                {
                    Id = 5,
                    Email = "klient1@ebocian.pl",
                    FirstName = "Andrzej",
                    LastName = "Kowalski",
                    PhoneNumber = "+48 998 997 999",
                    OwnedPoints = 0,
                    SpentPoints = 0
                },
                new Client
                {
                    Id = 6,
                    Email = "klient2@ebocian.pl",
                    FirstName = "Anna",
                    LastName = "Kowal",
                    PhoneNumber = "+48 111 222 333",
                    OwnedPoints = 0,
                    SpentPoints = 0
                }
            );
    }
}
