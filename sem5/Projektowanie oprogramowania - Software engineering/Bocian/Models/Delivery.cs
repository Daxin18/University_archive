using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class Delivery
{
    [DataType(DataType.DateTime)]
    public DateTimeOffset? DeliveredAt { get; set; }

    public required int CourierId { get; init; }
    public Courier? Courier { get; init; }

    [Key]
    public required int OrderId { get; init; }
    public Order? Order { get; init; }
}
