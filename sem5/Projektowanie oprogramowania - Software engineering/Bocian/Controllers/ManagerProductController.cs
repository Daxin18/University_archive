using Bocian.Common;
using Bocian.Services;
using Bocian.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Manager)]
[Route("manager/products")]
public sealed class ManagerProductController : Controller
{
    private readonly IManagerProductService _service;

    public ManagerProductController(IManagerProductService service) => _service = service;

    [HttpGet("")]
    public IActionResult Index(int? shopId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        ViewBag.shopId = shopId;

        var products = _service.AllAvailableProductsForShop((int) shopId);

        return View(products);
    }

    [HttpGet("noneLeft")]
    public IActionResult NoneLeft(int? shopId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        ViewBag.shopId = shopId;

        var products = _service.AllNoneLeftProductsForShop((int) shopId);

        return View("Index", products);
    }

    [HttpGet("create")]
    public IActionResult Create(int? shopId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });
        
        ViewBag.shopId = shopId;
        ViewBag.CategoryId = new SelectList(_service.GetCategories(), "Id", "Name");

        return View();
    }

    [HttpGet("edit")]
    public IActionResult Edit(int? shopId, int? productId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        if (productId is null)
            return RedirectToAction("Index", new { shopId });

        ViewBag.shopId = shopId;
        ViewBag.CategoryId = new SelectList(_service.GetCategories(), "Id", "Name");

        var product = _service.ManagerProductVm((int)shopId, (int)productId);

        return View(product);
    }

    [HttpGet("delete")]
    public IActionResult Delete(int? shopId, int? productId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        if (productId is null)
            return RedirectToAction("Index", new { shopId });

        ViewBag.shopId = shopId;
        ViewBag.CategoryId = _service.GetCategories().ToList();

        var product = _service.ManagerProductVm((int) shopId, (int) productId);

        return View(product);
    }

    [HttpPost("edit")]
    public IActionResult Edit(int? shopId, int? productId, [Bind("Id", "Name", "Description", "Price", "OnlyForAdults", "Weight", "CategoryId", "ImageUrl", "shopId", "Quantity")] ManagerProductVm product)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        if (productId is null)
            return RedirectToAction("Index", new { shopId });

        if (!ModelState.IsValid)
            return RedirectToAction("Index", new { shopId });

        _service.EditProduct((int)shopId, (int)productId, product);

        return RedirectToAction("Index", new { shopId });
    }

    [HttpPost("Create")]
    public IActionResult Create(int? shopId, [Bind("Id", "Name", "Description", "Price", "OnlyForAdults", "Weight", "CategoryId", "ImageUrl", "shopId", "Quantity")] ManagerProductVm product)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        if (!ModelState.IsValid)
            return RedirectToAction("Index", new { shopId });

        _service.AddProduct(product);

        return RedirectToAction("Index", new { shopId });
    }

    [HttpPost("delete")]
    public IActionResult DeleteConfirmed(int? shopId, int? productId)
    {
        if (shopId is null)
            return RedirectToAction("ShopForProducts", "Shop", new { managerId = User.Id() });

        if (productId is null)
            return RedirectToAction("Index", new { shopId });

        _service.DeleteProduct((int)shopId, (int)productId);

        return RedirectToAction("Index", new { shopId });
    }
}
