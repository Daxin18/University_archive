using System.ComponentModel.DataAnnotations;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class EmployeeOrderDetailsVm
{
    public required int Id { get; init; }

    [Display(Name = "Status")]
    public required OrderStatus Status { get; init; }

    [Display(Name = "Czas złożenia")]
    [DisplayFormat(DataFormatString = "{0:HH:mm yyyy-mm-dd}")]
    public required DateTime OrderedAt { get; init; }

    [Display(Name = "Klient")]
    public required string ClientFullName { get; init; }

    public required string AddressSummary { get; init; }

    public required string? ClientPhoneNumber { get; init; }

    [Display(Name = "Kwota")]
    [DataType(DataType.Currency)]
    public required decimal CartTotalPrice { get; init; }

    [Display(Name = "Produkty")]
    public required IEnumerable<EmployeeOrderDetailsCartItemVm> CartItems { get; init; }
}
