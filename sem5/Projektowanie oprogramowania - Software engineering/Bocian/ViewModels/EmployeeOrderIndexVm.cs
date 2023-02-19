namespace Bocian.ViewModels;

public sealed class EmployeeOrderIndexVm
{
    public required IEnumerable<EmployeeOrderIndexOrderVm> ConfirmedOrders { get; init; }

    public required IEnumerable<EmployeeOrderIndexOrderVm> PendingOrders { get; init; }
}
