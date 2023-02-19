using System.ComponentModel.DataAnnotations;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class DeliveryDetailsVm
{
    public required int Id { get; init; }

    [Display(Name = "Status")]
    public required OrderStatus Status { get; init; }

    [Display(Name = "Czas złożenia")]
    [DisplayFormat(DataFormatString = "{0:HH:mm yyyy-mm-dd}")]
    public required DateTime OrderedAt { get; init; }

    [Display(Name = "Sklep")]
    public required string EmployeeShopAddressSummary { get; init; }

    [Display(Name = "Klient")]
    public required string ClientFullName { get; init; }

    public required string AddressSummary { get; init; }

    public required string? ClientPhoneNumber { get; init; }

    [Display(Name = "Waga")]
    [DisplayFormat(DataFormatString = "{0} kg")]
    public required double CartTotalWeight { get; init; }

    public required string DeliveryCourierFullName { get; init; }

    public required string DeliveryCourierTransportationTypeName { get; init; }

    public string TrackingNumber => $"{Id.ToString().PadLeft(6, '0')}/{OrderedAt.Year}";

    public required IEnumerable<DeliveryDetailsCartItemVm> CartItems { get; init; }
}
