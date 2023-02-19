using System.ComponentModel.DataAnnotations;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class EmployeeOrderIndexOrderVm
{
    public required int Id { get; init; }

    [Display(Name = "Status")]
    public required OrderStatus Status { get; init; }

    [Display(Name = "Klient")]
    public required string AddressSummary { get; init; }

    public required string? ClientPhoneNumber { get; init; }

    public required int CartProductCount { get; init; }

    [DataType(DataType.Currency)]
    public required decimal CartTotalPrice { get; init; }
}
