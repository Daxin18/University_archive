namespace Bocian.ViewModels;

public sealed class DeliveryIndexVm
{
    public required IEnumerable<DeliveryIndexOrderVm> InDeliveryOrders { get; init; }

    public required IEnumerable<DeliveryIndexOrderVm> PackedOrders { get; init; }
}
