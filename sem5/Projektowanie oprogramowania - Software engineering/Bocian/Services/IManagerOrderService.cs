using Bocian.ViewModels;

namespace Bocian.Services;

public interface IManagerOrderService
{
	List<ManagerOrderVm> AllOrdersForShop(int shopId);
	List<ManagerOrderVm> AllDoneOrdersForShop(int shopId);
	List<ManagerOrderVm> AllInProgressOrdersForShop(int shopId);
	List<ManagerOrderVm> AllCanceledOrdersForShop(int shopId);
	bool DeleteOrder(int orderId);
}
