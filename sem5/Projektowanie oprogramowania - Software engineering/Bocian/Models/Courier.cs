using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class Courier : Account
{
    [DataType(DataType.Time)]
    public required TimeOnly AvailableFrom { get; set; }

    [DataType(DataType.Time)]
    public required TimeOnly AvailableTo { get; set; }

    public required int TransportationTypeId { get; set; }
    public TransportationType? TransportationType { get; set; }

    public ICollection<Delivery> Deliveries { get; set; } = new List<Delivery>();
}
