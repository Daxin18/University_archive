using Bocian.ViewModels;

namespace Bocian.Services;

public interface IShopService
{
	List<ShopIndexVm> AllManagerShopIndexVms(int managerId);
	ShopIndexVm ShopIndexVm(int shopId);
}
