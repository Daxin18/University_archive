using System.ComponentModel.DataAnnotations;

namespace Bocian.ViewModels;

public sealed class EmployeeOrderDetailsCartItemVm
{
    public required string ProductName { get; init; }

    public required int ItemCount { get; init; }

    [DataType(DataType.Currency)]
    public required decimal Price { get; init; }
}
