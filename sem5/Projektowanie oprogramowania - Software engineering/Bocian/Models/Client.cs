using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[Index(nameof(PhoneNumber), IsUnique = true)]
public sealed class Client : Account
{
    [Phone]
    [DataType(DataType.PhoneNumber)]
    [MaxLength(16)]
    public required string? PhoneNumber { get; set; }

    [Range(0, int.MaxValue)]
    public required int OwnedPoints { get; set; }

    [Range(0, int.MaxValue)]
    public required int SpentPoints { get; set; }

    public ICollection<Cart> Carts { get; set; } = new List<Cart>();
    public ICollection<Order> Orders { get; set; } = new List<Order>();
    public ICollection<FavoriteProduct> FavoriteProducts { get; set; } =
        new List<FavoriteProduct>();
}
