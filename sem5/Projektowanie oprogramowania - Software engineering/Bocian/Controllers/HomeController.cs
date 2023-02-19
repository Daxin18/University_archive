using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

public sealed class HomeController : Controller
{
    [HttpGet("")]
    public IActionResult Index() => View();

    [HttpGet("not-found")]
    public IActionResult ErrorNotFound() => View("Error", 404);
}
