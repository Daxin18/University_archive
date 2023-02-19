using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[Index(nameof(Name), IsUnique = true)]
public sealed class Product
{
    public int Id { get; init; }

    [MaxLength(255)]
    public required string Name { get; set; }

    [MaxLength(1000)]
    public required string Description { get; set; }

    [DataType(DataType.Currency)]
    [Precision(18, 2)]
    [Range(0, double.PositiveInfinity)]
    public required decimal Price { get; set; }

    public required bool OnlyForAdults { get; set; }

    [Range(0, double.PositiveInfinity)]
    public required double Weight { get; set; }

    public required int CategoryId { get; set; }
    public Category? Category { get; set; }

    public required int ImageId { get; set; }
    public Image? Image { get; set; }

    public ICollection<LoyaltyScheme> LoyaltySchemes { get; set; } = new List<LoyaltyScheme>();
}
