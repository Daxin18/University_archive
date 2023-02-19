using Bocian.Common;
using Bocian.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Manager)]
[Route("manager/orders")]
public sealed class ManagerOrderController : Controller
{
	private readonly IManagerOrderService _service;

	public ManagerOrderController(IManagerOrderService service) => _service = service;

	[HttpGet("")]
	public IActionResult Index(int? shopId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		ViewBag.shopId = shopId;
		var orders = _service.AllOrdersForShop((int) shopId);
		return View(orders);
	}

	[HttpGet("done")]
	public IActionResult Done(int? shopId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		ViewBag.shopId = shopId;
		var orders = _service.AllDoneOrdersForShop((int) shopId);
		return View("Index", orders);
	}

	[HttpGet("inprogress")]
	public IActionResult InProgress(int? shopId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		ViewBag.shopId = shopId;
		var orders = _service.AllInProgressOrdersForShop((int) shopId);
		return View("Index", orders);
	}

	[HttpGet("canceled")]
	public IActionResult Canceled(int? shopId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		ViewBag.shopId = shopId;
		var orders = _service.AllCanceledOrdersForShop((int) shopId);
		return View("Index", orders);
	}

	[HttpGet("delete")]
	public IActionResult Delete(int? shopId, int? orderId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		if (orderId is null)
			return RedirectToAction("Index", new { shopId });

		ViewBag.shopId = shopId;
		var order = _service
			.AllInProgressOrdersForShop((int) shopId)
			.FirstOrDefault(o => o.Id == orderId);

		return View(order);
	}

	[HttpGet("deleteConfirmed")]
	public IActionResult DeleteConfirmed(int? shopId, int? orderId)
	{
		if (shopId is null)
			return RedirectToAction("ShopForOrders", "Shop", new { managerId = User.Id() });

		if (orderId is null)
			return RedirectToAction("Index", new { shopId });

		ViewBag.shopId = shopId;
		_service.DeleteOrder((int) orderId);

		return RedirectToAction("Index", new { shopId });
	}
}
