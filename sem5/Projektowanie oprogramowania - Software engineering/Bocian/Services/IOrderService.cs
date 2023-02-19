using Bocian.ViewModels;

namespace Bocian.Services;

public interface IOrderService
{
    Task<EmployeeOrderIndexVm> AllForEmployeeAsync(int employeeId);

    Task<EmployeeOrderDetailsVm> DetailsForEmployeeAsync(int orderId, int employeeId);

    Task<DeliveryIndexVm> AllForCourierAsync(int courierId);

    Task<DeliveryDetailsVm> DetailsForCourierAsync(int orderId, int employeeId);

    Task ConfirmAsync(int orderId, int employeeId);

    Task CancelAsync(int orderId);

    Task PackAsync(int orderId, int employeeId);

    Task DeliverAsync(int orderId, int courierId);

    Task CompleteAsync(int orderId, int courierId);
}
