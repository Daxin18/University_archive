using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class Complaint
{
    public int Id { get; init; }

    [DataType(DataType.MultilineText)]
    public required string Reason { get; init; }

    [DataType(DataType.Date)]
    public required DateOnly Date { get; init; }

    public int OrderId { get; init; }
    public Order? Order { get; init; }
}
