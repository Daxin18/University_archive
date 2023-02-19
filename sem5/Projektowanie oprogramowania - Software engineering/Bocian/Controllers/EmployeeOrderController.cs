using Bocian.Common;
using Bocian.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Employee)]
[Route("employee/orders")]
public sealed class EmployeeOrderController : Controller
{
    private readonly IOrderService _orderService;

    public EmployeeOrderController(IOrderService orderService) => _orderService = orderService;

    [HttpGet("")]
    public async Task<IActionResult> Index()
    {
        var model = await _orderService.AllForEmployeeAsync(User.Id());
        return View(model);
    }

    [HttpGet("{id:int}")]
    public async Task<IActionResult> Details(int id)
    {
        var model = await _orderService.DetailsForEmployeeAsync(id, User.Id());
        return View(model);
    }

    [HttpPost("{id:int}/confirm")]
    public async Task<IActionResult> Confirm(int id)
    {
        await _orderService.ConfirmAsync(id, User.Id());
        TempData["Dialog"] = "Zamówienie oznaczone jako zatwierdzone.";
        return RedirectToAction(nameof(Details), new { id });
    }

    [HttpPost("{id:int}/cancel")]
    public async Task<IActionResult> Cancel(int id)
    {
        await _orderService.CancelAsync(id);
        TempData["Dialog"] = "Zamówienie oznaczone jako anulowane.";
        return RedirectToAction(nameof(Index));
    }

    [HttpPost("{id:int}/pack")]
    public async Task<IActionResult> Pack(int id)
    {
        await _orderService.PackAsync(id, User.Id());
        TempData["Dialog"] = "Zamówienie oznaczone jako spakowane.";
        return RedirectToAction(nameof(Index));
    }
}
