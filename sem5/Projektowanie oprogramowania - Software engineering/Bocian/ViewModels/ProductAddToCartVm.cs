using Bocian.Models;

namespace Bocian.ViewModels;

public class ProductAddToCartVm
{
    public Product? Product { get; set; }
    public bool IsInFavorites { get; set; }
    public string ImageUrl { get; set; } = "";
    public string CategoryName { get; set; } = "";
    public int Quantity { get; set; } = 1;
    public int CartId { get; set; } = -1;
    public int ProductId { get; set; } = -1;
}

