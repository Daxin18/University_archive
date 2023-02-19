using Bocian.Common;
using Bocian.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Courier)]
[Route("courier/deliveries")]
public sealed class DeliveryController : Controller
{
    private readonly IOrderService _orderService;

    public DeliveryController(IOrderService orderService) => _orderService = orderService;

    [HttpGet("")]
    public async Task<IActionResult> Index()
    {
        var model = await _orderService.AllForCourierAsync(User.Id());
        return View(model);
    }

    [HttpGet("{id:int}")]
    public async Task<IActionResult> Details(int id)
    {
        var model = await _orderService.DetailsForCourierAsync(id, User.Id());
        return View(model);
    }

    [HttpPost("{id:int}/deliver")]
    public async Task<IActionResult> Deliver(int id)
    {
        await _orderService.DeliverAsync(id, User.Id());
        TempData["Dialog"] = "Zamówienie oznaczone jako dostarczane.";
        return RedirectToAction(nameof(Details), new { id });
    }

    [HttpPost("{id:int}/complete")]
    public async Task<IActionResult> Complete(int id)
    {
        await _orderService.CompleteAsync(id, User.Id());
        TempData["Dialog"] = "Zamówienie oznaczone jako zrealizowane.";
        return RedirectToAction(nameof(Index));
    }
}
