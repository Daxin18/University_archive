using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

public sealed class Payment
{
    public int Id { get; init; }

    public required PaymentType PaymentType { get; init; }

    [DataType(DataType.Currency)]
    [Precision(18, 2)]
    [Range(0, double.PositiveInfinity)]
    public required decimal TotalPrice { get; init; }

    [DataType(DataType.DateTime)]
    public required DateTime? PaymentTime { get; set; }

    public Order? Order { get; set; }
}
