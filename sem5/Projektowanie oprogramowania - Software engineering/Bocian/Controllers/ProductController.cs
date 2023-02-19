using Bocian.Data;
using Bocian.ViewModels;
using Bocian.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Bocian.Common;
using Microsoft.CodeAnalysis;
using Microsoft.AspNetCore.Authorization;
using System.Data;

namespace Bocian.Controllers;

// TODO: authorize paths with different roles (Client for GET, Manager for POST/PUT/DELETE)
[Route("products")]
public sealed class ProductController : Controller
{
    private readonly ShopDbContext _context;

    public ProductController(ShopDbContext context)
    {
        _context = context;
    }

    [Authorize(Roles = Roles.Client)]
    public IActionResult Index(ProductIndexVm productIndexVm)
    {
        if (ModelState.IsValid)
        {
            productIndexVm.Categories = _context.Categories.ToList().Select(x => new SelectListItem()
            {
                Text = x.Name,
                Value = x.Id.ToString()
            }).ToList();

            var productsFromCategory = new List<Product>();

            if (productIndexVm.SelectedCategoryId >= 1)
            {
                productsFromCategory = _context.Products.Where(x => x.CategoryId == productIndexVm.SelectedCategoryId).ToList();
            }
            else
            {
                productsFromCategory = _context.Products.ToList();
            }

            var finalProductList = new List<(Product, bool, Image, Category)>();

            foreach (var product in productsFromCategory)
            {
                var isInFavorites = _context.FavoriteProducts
                                        .Where(x => x.ClientId == GetUserId())
                                        .Where(x => x.ProductId == product.Id)
                                        .ToList()
                                        .Count() > 0;

                var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
                var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

                finalProductList.Add((product, isInFavorites, image, category));
            }

            productIndexVm.Products = finalProductList;
        }

        return View(productIndexVm);
    }

    [Route("products/addToCart")]
    [Authorize(Roles = Roles.Client)]
    public async Task<IActionResult> AddToCart(int productId)
    {
        var product = _context.Products.FirstOrDefault(x => x.Id == productId);

        if (product is not null)
        {
            var isInFavorites = _context.FavoriteProducts
                                        .Where(x => x.ClientId == GetUserId())
                                        .Where(x => x.ProductId == product.Id)
                                        .ToList()
                                        .Count() > 0;

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
                        Id = 123456789,
                        ClientId = GetUserId()
                    });
                    await _context.SaveChangesAsync();
                }
            }

            var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
            var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

            ProductAddToCartVm viewModel = new ProductAddToCartVm()
            {
                Product = product,
                IsInFavorites = isInFavorites,
                ImageUrl = image.Url,
                CategoryName = category.Name,
                Quantity = 1,
                CartId = cartId,
                ProductId = product.Id
            };
            return View(viewModel);
        }
        // product is null - fix it
        return RedirectToAction(nameof(Index));
    }

    [HttpPost]
    [Authorize(Roles = Roles.Client)]
    public async Task<IActionResult> AddToCarts([Bind("Product,IsInFavorites,ImageUrl,CategoryName,Quantity,CartId,ProductId")] ProductAddToCartVm viewModel)
    {
        if (ModelState.IsValid)
        {
            if(viewModel.Quantity <= 0)
            {
                return RedirectToAction("QuantityError", new { productId = viewModel.ProductId});
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
            await _context.SaveChangesAsync();

            viewModel.Product = _context.Products
                                            .Where(x => x.Id == viewModel.ProductId)
                                            .ToList()[0];

            return RedirectToAction("ProductAdded", new { productId = viewModel.ProductId});
        }
        return RedirectToAction(nameof(Index));
    }

    [Route("products/addToCart/QuantityError")]
    [Authorize(Roles = Roles.Client)]
    public IActionResult QuantityError(int productId)
    {
        ViewBag.ProductId = productId;
        return View();
    }
    [Route("products/addToCart/productAdded")]
    [Authorize(Roles = Roles.Client)]
    public async Task<IActionResult> ProductAdded(int productId)
    {
        var product = _context.Products.FirstOrDefault(x => x.Id == productId);

        if (product is not null)
        {
            var isInFavorites = _context.FavoriteProducts
                                        .Where(x => x.ClientId == GetUserId())
                                        .Where(x => x.ProductId == product.Id)
                                        .ToList()
                                        .Count() > 0;

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

            var image = _context.Images.Where(x => x.Id == product.Id).ToList()[0];
            var category = _context.Categories.Where(x => x.Id == product.CategoryId).ToList()[0];

            ProductAddToCartVm viewModel = new ProductAddToCartVm()
            {
                Product = product,
                IsInFavorites = isInFavorites,
                ImageUrl = image.Url,
                CategoryName = category.Name,
                Quantity = 1,
                CartId = cartId,
                ProductId = product.Id
            };
            return View(viewModel);
        }
        // product is null - fix it
        return RedirectToAction(nameof(Index));
    }

    [Route("products/addToCart/goBack")]
    [Authorize(Roles = Roles.Client)]
    public IActionResult GoBack()
    {
        return RedirectToAction(nameof(Index));
    }

    [Route("products/addToFavorites")]
    [Authorize(Roles = Roles.Client)]
    public async Task<IActionResult> AddToFavorites(int productId)
    {
        var clientId = GetUserId();

        var newFavorite = new FavoriteProduct()
        {
            ClientId = clientId,
            ProductId = productId,
            LikedAt = DateTimeOffset.UtcNow
        };

        _context.Add(newFavorite);
        await _context.SaveChangesAsync();

        return RedirectToAction(nameof(Index));
    }

    [Route("products/RemoveFromFavorites")]
    [Authorize(Roles = Roles.Client)]
    public async Task<IActionResult> RemoveFromFavorites(int productId)
    {
        var clientId = GetUserId();

        var product = await _context.FavoriteProducts.FindAsync(clientId, productId);

        if (product is not null)
        {
            _context.FavoriteProducts.Remove(product);
            await _context.SaveChangesAsync();
        }

        return RedirectToAction(nameof(Index));
    }

    private int GetUserId()
    {
        var account = _context.Accounts.Where(x => x.Email.Equals(User.Email())).ToList();

        if(account.Count == 0)
            return -1;

        return account[0].Id;
    }
}
