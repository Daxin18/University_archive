using System.ComponentModel.DataAnnotations;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class DeliveryIndexOrderVm
{
    public required int Id { get; init; }

    [Display(Name = "Status")]
    public required OrderStatus Status { get; init; }

    [Display(Name = "Sklep")]
    public required string EmployeeShopAddressSummary { get; init; }

    [Display(Name = "Klient")]
    public required string AddressSummary { get; init; }

    public required string? ClientPhoneNumber { get; init; }

    [Display(Name = "Waga")]
    [DisplayFormat(DataFormatString = "{0} kg")]
    public required double CartTotalWeight { get; init; }
}
