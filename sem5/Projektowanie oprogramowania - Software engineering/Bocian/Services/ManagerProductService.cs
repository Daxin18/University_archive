using Bocian.Data;
using Bocian.Models;
using Bocian.ViewModels;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

public class ManagerProductService : IManagerProductService
{

    private readonly ShopDbContext _context;

    public ManagerProductService(ShopDbContext context) => _context = context;

    public List<ManagerProductVm> AllProductsForShop(int shopId)
    {
        var shopProducts = _context.ProductCount
            .Where(p => p.ShopId == shopId).ToList();

        var products = _context.Products
            .Include(p => p.Image)
            .Where(p => shopProducts.Select(sp => sp.ProductId).Contains(p.Id))
            .ToList();

        var productVms = products
            .Select(p => new ManagerProductVm
            {
                Id = p.Id,
                Name = p.Name,
                Description = p.Description,
                Price = p.Price,
                OnlyForAdults = p.OnlyForAdults,
                Weight = p.Weight,
                ImageUrl = p.Image!.Url,
                shopId = shopId,
                Quantity = shopProducts.FirstOrDefault(sp => sp.ProductId == p.Id)!.Quantity,
                CategoryId = p.CategoryId,
            })
            .ToList();

        return productVms;
    }

    public List<ManagerProductVm> AllNoneLeftProductsForShop(int shopId)
    {
        return AllProductsForShop(shopId)
            .Where(p => p.Quantity <= 0)
            .ToList();
    }

    public List<ManagerProductVm> AllAvailableProductsForShop(int shopId)
    {
        return AllProductsForShop(shopId)
            .Where(p => p.Quantity > 0)
            .ToList();
    }

    public ManagerProductVm ManagerProductVm(int shopId, int productId)
    {
        return AllProductsForShop(shopId)
            .FirstOrDefault(p => p.Id == productId)!;
    }

    public bool AddProduct(ManagerProductVm managerProductVm)
    {
        var image = new Image
        {
            Url = managerProductVm.ImageUrl
        };

        var img = _context.Add(image);
        _context.SaveChanges();

        var product = new Product
        {
            Name = managerProductVm.Name,
            CategoryId = managerProductVm.CategoryId,
            Description = managerProductVm.Description,
            ImageId = img.Entity.Id,
            Price = managerProductVm.Price,
            OnlyForAdults = managerProductVm.OnlyForAdults,
            Weight = managerProductVm.Weight
        };

        
        var prod = _context.Add(product);
        _context.SaveChanges();

        var productCount = new ProductCount
        {
            ProductId = prod.Entity.Id,
            ShopId = managerProductVm.shopId,
            Quantity = managerProductVm.Quantity
        };

        _context.Add(productCount);
        _context.SaveChanges();

        return true;
    }

    public bool EditProduct(int shopId, int productId, ManagerProductVm managerProductVm)
    {
        var product = _context.Products.Find(productId);
        if (product is null)
            return false;
        var imageId = product.ImageId;
        var image = _context.Images.Find(imageId);
        var productCountId = _context.ProductCount.First(p => p.ProductId == productId).Id;
        var productCount = _context.ProductCount.Find(productCountId);
        if (productCount is null)
            return false;

        product.Name = managerProductVm.Name;
        product.Description = managerProductVm.Description;
        product.Price = managerProductVm.Price;
        product.Weight = managerProductVm.Weight;
        product.OnlyForAdults = managerProductVm.OnlyForAdults;
        product.CategoryId = managerProductVm.CategoryId;
        productCount.Quantity = managerProductVm.Quantity;
        if (image is not null)
        {
            image.Url = managerProductVm.ImageUrl;
            _context.Update(image);
        }
        _context.Update(product);
        _context.Update(productCount);
        _context.SaveChanges();
        return true;
    }

    public bool DeleteProduct(int shopId, int productId)
    {
        var product = _context.Products.Find(productId);
        if (product is null)
            return false;
        var imageId = product.ImageId;
        _context.Products.Remove(product);

        var productCountId = _context.ProductCount.First(p => p.ProductId == productId).Id;
        var productCount = _context.ProductCount.Find(productCountId);
        if (productCount is null)
            return false;
        _context.ProductCount.Remove(productCount);

        var image = _context.Images.Find(imageId);
        if (image is not null)
            _context.Images.Remove(image);

        _context.SaveChanges();
        return true;
    }

    public DbSet<Category> GetCategories()
    {
        return _context.Categories;
    }
}
