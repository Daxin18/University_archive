using Bocian.Common;
using Bocian.Data;
using Bocian.Models;
using Bocian.ViewModels;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace Bocian.Controllers;


[Route("loyalty-schemes")]
[Authorize(Roles = Roles.Client)]
public sealed class LoyaltyController : Controller
{
    private readonly ShopDbContext _context;
    public LoyaltyController(ShopDbContext context)
    {
        _context = context;
    }
    public IActionResult Index(LoyaltyIndexVm loyaltyindexVm)
    {
        if (ModelState.IsValid)
        {
            loyaltyindexVm.Categories = _context.Categories.ToList().Select(x => new SelectListItem()
            {
                Text = x.Name,
                Value = x.Id.ToString()
            }).ToList();

            var loyaltiesFromCategory = new List<LoyaltyScheme>();

            if (loyaltyindexVm.SelectedCategoryId >= 1)
            {
                loyaltiesFromCategory = _context.LoyaltySchemes.Where(x => x.Product.CategoryId == loyaltyindexVm.SelectedCategoryId).ToList();
            }
            else
            {
                loyaltiesFromCategory = _context.LoyaltySchemes.ToList();
            }

            var finalLoyaltyList = new List<(LoyaltyScheme, Product, Image, Category)>();
            var now = DateOnly.FromDateTime(DateTime.Now);

            foreach (var loyalty in loyaltiesFromCategory)
            {
                if (loyalty.StartDate < now && loyalty.EndDate > now)
                {
                    var product = _context.Products.Where(x => x.Id == loyalty.ProductId).ToList()[0];
                    var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
                    var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

                    finalLoyaltyList.Add((loyalty, product, image, category));
                }
            }

            loyaltyindexVm.LoyaltySchemes = finalLoyaltyList;
        }

        return View(loyaltyindexVm);
    }

    [Route("loyalty-schemes/addToCart")]
    public async Task<IActionResult> AddToCart(int loyaltyId)
    {
        var loyalty = _context.LoyaltySchemes.FirstOrDefault(x => x.Id == loyaltyId);

        if (loyalty is not null)
        {
            var cartId = -1;
            while (cartId < 0)
            {
                var cart = _context.Carts
                                        .Where(x => x.ClientId == GetUserId())
                                        .ToList();
                if (cart.Count > 0)
                {
                    cartId = cart.First().Id;
                }
                else
                {
                    _context.Add(new Cart()
                    {
                        Id = 69,
                        ClientId = GetUserId()
                    });
                    await _context.SaveChangesAsync();
                }
            }

            var product = _context.Products.Where(x => x.Id == loyalty.ProductId).ToList()[0];
            var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
            var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

            LoyaltyAddToCartVm viewModel = new LoyaltyAddToCartVm()
            {
                Loyalty = loyalty,
                Product = product,
                ImageUrl = image.Url,
                CategoryName = category.Name,
                Quantity = 1,
                CartId = cartId,
                ProductId = product.Id,
                LoyaltyId = loyaltyId,
                LoyaltyPointPrice = loyalty.PointPrice
            };
            return View(viewModel);
        }
        // product is null - fix it
        return RedirectToAction(nameof(Index));
    }

    [HttpPost]
    public async Task<IActionResult> AddToCarts([Bind("Loyalty,Product,ImageUrl,CategoryName,Quantity,CartId,ProductId,LoyaltyId,LoyaltyPointPrice")] LoyaltyAddToCartVm viewModel)
    {
        if (ModelState.IsValid)
        {
            if (viewModel.Quantity <= 0)
            {
                return RedirectToAction("QuantityError", new { loyaltyId = viewModel.LoyaltyId, message = "Podano niewłaściwą ilość produktu :(" });
            }

            var userOwnedPoints = _context.Clients
                                        .Where(x => x.Id == GetUserId())
                                        .Select(x => x.OwnedPoints)
                                        .ToList()[0];
            var userSpentPoints = _context.Clients
                                        .Where(x => x.Id == GetUserId())
                                        .Select(x => x.SpentPoints)
                                        .ToList()[0];

            var pointsRemaining = (userOwnedPoints - userSpentPoints) - (viewModel.Quantity * viewModel.LoyaltyPointPrice);

            if (pointsRemaining < 0)
            {
                return RedirectToAction("QuantityError", new { loyaltyId = viewModel.LoyaltyId, message = "Masz za mało Boćków :(" });
            }

            var existingCartItems = _context.CartItems
                                            .Where(x => x.CartId == viewModel.CartId)
                                            .Where(x => x.ProductId == viewModel.ProductId)
                                            .ToList();
            CartItem newCartItem;

            if (existingCartItems.Count == 0)
            {
                newCartItem = new CartItem()
                {
                    CartId = viewModel.CartId,
                    ProductId = viewModel.ProductId,
                    ItemCount = viewModel.Quantity
                };
            }
            else
            {
                var existingCartItem = existingCartItems[0];
                newCartItem = existingCartItem;
                newCartItem.ItemCount += viewModel.Quantity;

                _context.CartItems.Remove(existingCartItem);
                _context.SaveChanges();
            }

            _context.CartItems.Add(newCartItem);

            var client = _context.Clients.FirstOrDefault(x => x.Id == GetUserId());
            if (client != null)
            {
                client.SpentPoints = client.OwnedPoints - pointsRemaining;
                _context.Clients.Update(client);
            }
            await _context.SaveChangesAsync();

            viewModel.Product = _context.Products
                                            .Where(x => x.Id == viewModel.ProductId)
                                            .ToList()[0];

            return RedirectToAction("ProductAdded", new { loyaltyId = viewModel.LoyaltyId });
        }
        return RedirectToAction(nameof(Index));
    }

    [Route("loyalty-schemes/addToCart/productAdded")]
    public async Task<IActionResult> ProductAdded(int loyaltyId)
    {
        var loyalty = _context.LoyaltySchemes.FirstOrDefault(x => x.Id == loyaltyId);

        if (loyalty is not null)
        {
            var cartId = -1;
            while (cartId < 0)
            {
                var cart = _context.Carts
                                        .Where(x => x.ClientId == GetUserId())
                                        .ToList();
                if (cart.Count > 0)
                {
                    cartId = cart.First().Id;
                }
                else
                {
                    _context.Add(new Cart()
                    {
                        Id = 69,
                        ClientId = GetUserId()
                    });
                    await _context.SaveChangesAsync();
                }
            }

            var product = _context.Products.Where(x => x.Id == loyalty.ProductId).ToList()[0];
            var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
            var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

            LoyaltyAddToCartVm viewModel = new LoyaltyAddToCartVm()
            {
                Loyalty = loyalty,
                Product = product,
                ImageUrl = image.Url,
                CategoryName = category.Name,
                Quantity = 1,
                CartId = cartId,
                ProductId = product.Id,
                LoyaltyId = loyalty.Id,
                LoyaltyPointPrice = loyalty.PointPrice
            };
            return View(viewModel);
        }
        // product is null - fix it
        return RedirectToAction(nameof(Index));
    }

    [Route("loyalty-schemes/show-points")]
    public IActionResult ShowPoints()
    {
        var userOwnedPoints = _context.Clients
                            .Where(x => x.Id == GetUserId())
                            .Select(x => x.OwnedPoints)
                            .ToList()[0];
        var userSpentPoints = _context.Clients
                                    .Where(x => x.Id == GetUserId())
                                    .Select(x => x.SpentPoints)
                                    .ToList()[0];

        var pointsRemaining = (userOwnedPoints - userSpentPoints);

        ViewBag.PointsRemaining = pointsRemaining;

        return View();
    }

    [Route("loyalty-schemes/addToCart/quantityError")]
    public IActionResult QuantityError(int loyaltyId, string message)
    {
        ViewBag.Message = message;
        ViewBag.LoyaltyId = loyaltyId;
        return View();
    }
    [Route("loyalty-schemes/addToCart/goBack")]
    public IActionResult GoBack()
    {
        return RedirectToAction(nameof(Index));
    }

    private int GetUserId()
    {
        var account = _context.Accounts.Where(x => x.Email.Equals(User.Email())).ToList();

        if (account.Count == 0)
            return -1;

        return account[0].Id;
    }
}
