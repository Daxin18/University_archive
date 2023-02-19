using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class LoyaltyScheme
{
    public int Id { get; init; }

    [Range(0, int.MaxValue)]
    public required int PointPrice { get; init; }

    [DataType(DataType.Date)]
    public required DateOnly StartDate { get; set; }

    [DataType(DataType.Date)]
    public DateOnly? EndDate { get; set; }

    public required int ProductId { get; set; }
    public Product? Product { get; set; }
    [DataType(DataType.Currency)]
    [Precision(18, 2)]
    [Range(0, double.PositiveInfinity)]
    public required decimal DiscountPrice { get; set; }
}
