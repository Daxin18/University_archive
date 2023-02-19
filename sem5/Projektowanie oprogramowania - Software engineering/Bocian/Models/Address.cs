using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Bocian.Models;

public sealed class Address
{
    public int Id { get; init; }

    [DataType(DataType.PostalCode)]
    [MinLength(6), MaxLength(6)]
    [RegularExpression(@"^\d{2}-\d{3}")]
    public required string PostalCode { get; init; }

    [MaxLength(255)]
    public required string City { get; init; }

    [MaxLength(255)]
    public required string Street { get; init; }

    [Range(1, int.MaxValue)]
    public required int HouseNumber { get; init; }

    [Range(1, int.MaxValue)]
    public required int? ApartmentNumber { get; init; }

    [NotMapped]
    public string FullNumber =>
        ApartmentNumber is { } apartmentNumber
            ? $"{HouseNumber}/{apartmentNumber}"
            : HouseNumber.ToString();

    [NotMapped]
    public string Summary => $"ul. {Street} {FullNumber}, {PostalCode} {City}";
}
