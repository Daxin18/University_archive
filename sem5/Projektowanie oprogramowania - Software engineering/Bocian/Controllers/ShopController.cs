using Bocian.Common;
using Bocian.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Manager)]
[Route("manager/shops")]
public sealed class ShopController : Controller
{
	private readonly IShopService _service;

	public ShopController(IShopService service) => _service = service;

	//TODO pewnie nigdy bo nie warto: zmienić to w jedno zapytanie które pamięta skąd przyszło i gdzie ma wrócić xD

	[HttpGet("orders")]
	public IActionResult ShopForOrders(int managerId, int? shopId)
	{
		if (shopId is not null) 
			return RedirectToAction("Index", "ManagerOrder", new { shopId });

		var shops = _service.AllManagerShopIndexVms(managerId);
		return View(shops);
	}

	[HttpGet("products")]
	public IActionResult ShopForProducts(int managerId, int? shopId)
	{
		if (shopId is not null)
			return RedirectToAction("Index", "ManagerProduct", new { shopId });

		var shops = _service.AllManagerShopIndexVms(managerId);
		return View(shops);
	}
}
