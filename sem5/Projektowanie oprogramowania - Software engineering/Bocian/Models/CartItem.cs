using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[PrimaryKey(nameof(ProductId), nameof(CartId))]
public sealed class CartItem
{
    public required int ProductId { get; init; }
    public Product? Product { get; init; }

    public required int CartId { get; init; }
    public Cart? Cart { get; init; }

    [Range(1, int.MaxValue)]
    public required int ItemCount { get; set; }

    [NotMapped]
    public decimal Price => ItemCount * Product?.Price ?? 0;

    [NotMapped]
    public double Weight => ItemCount * Product?.Weight ?? 0;
}
