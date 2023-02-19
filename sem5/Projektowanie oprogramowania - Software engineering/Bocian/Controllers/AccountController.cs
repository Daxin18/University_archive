using System.Security.Claims;
using Bocian.Common;
using Bocian.Services;
using Bocian.ViewModels;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Route("account")]
public sealed class AccountController : Controller
{
    private readonly IAuthService _authService;

    public AccountController(IAuthService authService) => _authService = authService;

    [Authorize]
    [HttpGet("")]
    public async Task<IActionResult> Details()
    {
        var accountDetails = await _authService.GetAccountDetails(User.Email());

        if (accountDetails is null)
            return RedirectToAction(nameof(ErrorForbidden));

        return View(accountDetails);
    }

    [HttpGet("login")]
    public IActionResult Login() => View();

    [HttpPost("login")]
    public async Task<IActionResult> Login(AccountLoginVm model)
    {
        if (!ModelState.IsValid)
            return View(model);

        var identity = await _authService.AuthenticateUser(model.Email, model.Password);

        if (identity is null)
        {
            ModelState.AddModelError(string.Empty, "Nie znaleziono konta.");
            return View(model);
        }

        await HttpContext.SignInAsync(
            CookieAuthenticationDefaults.AuthenticationScheme,
            new ClaimsPrincipal(identity)
        );

        return RedirectToAction(nameof(Details));
    }

    [Authorize]
    [HttpPost("logout")]
    public async Task<IActionResult> Logout()
    {
        await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);

        return RedirectToAction(nameof(Login));
    }

    [HttpGet("~/forbidden")]
    public IActionResult ErrorForbidden() => View("Error", 403);
}
