using Bocian.Data;
using Bocian.Models;
using Bocian.ViewModels;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

public interface IManagerProductService
{
    List<ManagerProductVm> AllProductsForShop(int shopId);
    List<ManagerProductVm> AllNoneLeftProductsForShop(int shopId);
    List<ManagerProductVm> AllAvailableProductsForShop(int shopId);
    ManagerProductVm ManagerProductVm(int shopId, int productId);
    bool AddProduct(ManagerProductVm managerProductVm);
    bool EditProduct(int shopId, int productId, ManagerProductVm managerProductVm);
    bool DeleteProduct(int shopId, int productId);
    DbSet<Category> GetCategories();
}
