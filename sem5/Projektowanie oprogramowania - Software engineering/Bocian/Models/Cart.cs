using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Bocian.Models;

public sealed class Cart
{
    public int Id { get; init; }

    public required int ClientId { get; init; }
    public Client? Client { get; init; }

    public Order? Order { get; set; }
    public ICollection<CartItem> Items { get; set; } = new List<CartItem>();

    [NotMapped]
    public int ProductCount => Items.Sum(ci => ci.ItemCount);

    [NotMapped]
    [DataType(DataType.Currency)]
    public decimal TotalPrice => Items.Sum(ci => ci.Price);

    [NotMapped]
    public double TotalWeight => Items.Sum(ci => ci.Weight);
}
