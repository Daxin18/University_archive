using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class OrderSeeder
{
    public static void SeedOrders(this ModelBuilder modelBuilder)
    {
        modelBuilder
            .Entity<Payment>()
            .HasData(
                new Payment
                {
                    Id = 1,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 2,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 3,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 4,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1001,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1002,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1003,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1004,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1005,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                },
                new Payment
                {
                    Id = 1006,
                    PaymentType = PaymentType.BankTransfer,
                    TotalPrice = 0m,
                    PaymentTime = null
                }
            );

        modelBuilder
            .Entity<Cart>()
            .HasData(
                new Cart { Id = 1, ClientId = 4 },
                new Cart { Id = 2, ClientId = 4 },
                new Cart { Id = 3, ClientId = 5 },
                new Cart { Id = 4, ClientId = 6 },
                new Cart { Id = 1001, ClientId = 4 },
                new Cart { Id = 1002, ClientId = 5 },
                new Cart { Id = 1003, ClientId = 6 },
                new Cart { Id = 1004, ClientId = 4 },
                new Cart { Id = 1005, ClientId = 5 },
                new Cart { Id = 1006, ClientId = 6 }
            );

        modelBuilder
            .Entity<CartItem>()
            .HasData(
                new CartItem
                {
                    CartId = 1,
                    ProductId = 1,
                    ItemCount = 1
                },
                new CartItem
                {
                    CartId = 1,
                    ProductId = 2,
                    ItemCount = 3
                },
                new CartItem
                {
                    CartId = 1001,
                    ProductId = 1,
                    ItemCount = 5
                },
                new CartItem
                {
                    CartId = 1002,
                    ProductId = 2,
                    ItemCount = 2
                },
                new CartItem
                {
                    CartId = 1003,
                    ProductId = 3,
                    ItemCount = 7
                },
                new CartItem
                {
                    CartId = 1004,
                    ProductId = 4,
                    ItemCount = 3
                },
                new CartItem
                {
                    CartId = 1005,
                    ProductId = 1,
                    ItemCount = 1
                },
                new CartItem
                {
                    CartId = 1006,
                    ProductId = 2,
                    ItemCount = 25
                }
            );

        modelBuilder
            .Entity<Order>()
            .HasData(
                new Order
                {
                    Id = 1,
                    Status = OrderStatus.Pending,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 12, 9, 37, 10)),
                    ClientId = 4,
                    AddressId = 2,
                    PaymentId = 1,
                    CartId = 1
                },
                new Order
                {
                    Id = 2,
                    Status = OrderStatus.Pending,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 12, 15, 18, 5)),
                    ClientId = 4,
                    AddressId = 3,
                    PaymentId = 2,
                    CartId = 2
                },
                new Order
                {
                    Id = 3,
                    Status = OrderStatus.Pending,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 11, 4, 13, 18)),
                    ClientId = 5,
                    AddressId = 4,
                    PaymentId = 3,
                    CartId = 3
                },
                new Order
                {
                    Id = 4,
                    Status = OrderStatus.Pending,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 15, 21, 37, 59)),
                    ClientId = 6,
                    AddressId = 5,
                    PaymentId = 4,
                    CartId = 4
                },
                new Order
                {
                    Id = 1001,
                    Status = OrderStatus.Confirmed,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 12, 9, 37, 10)),
                    ClientId = 4,
                    AddressId = 1004,
                    PaymentId = 1001,
                    CartId = 1001,
                    EmployeeId = 1001
                },
                new Order
                {
                    Id = 1002,
                    Status = OrderStatus.Packed,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 13, 9, 37, 10)),
                    ClientId = 5,
                    AddressId = 1003,
                    PaymentId = 1002,
                    CartId = 1002,
                    EmployeeId = 1001
                },
                new Order
                {
                    Id = 1003,
                    Status = OrderStatus.InDelivery,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 14, 9, 37, 10)),
                    ClientId = 6,
                    AddressId = 1002,
                    PaymentId = 1003,
                    CartId = 1003,
                    EmployeeId = 1001
                },
                new Order
                {
                    Id = 1004,
                    Status = OrderStatus.Completed,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 15, 9, 37, 10)),
                    ClientId = 4,
                    AddressId = 1001,
                    PaymentId = 1004,
                    CartId = 1004,
                    EmployeeId = 1001
                },
                new Order
                {
                    Id = 1005,
                    Status = OrderStatus.Canceled,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 16, 9, 37, 10)),
                    ClientId = 5,
                    AddressId = 2,
                    PaymentId = 1005,
                    CartId = 1005,
                    EmployeeId = 1001
                },
                new Order
                {
                    Id = 1006,
                    Status = OrderStatus.Disputed,
                    OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 17, 9, 37, 10)),
                    ClientId = 6,
                    AddressId = 1,
                    PaymentId = 1006,
                    CartId = 1006,
                    EmployeeId = 1001
                }
            );
    }
}
