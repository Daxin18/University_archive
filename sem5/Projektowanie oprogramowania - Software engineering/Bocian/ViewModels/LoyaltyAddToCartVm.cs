using Microsoft.AspNetCore.Mvc.Rendering;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class LoyaltyAddToCartVm
{
    public LoyaltyScheme? Loyalty { get; set; }
    public Product? Product { get; set; }
    public string ImageUrl { get; set; } = "";
    public string CategoryName { get; set; } = "";
    public int Quantity { get; set; } = 1;
    public int CartId { get; set; } = -1;
    public int ProductId { get; set; } = -1;
    public int LoyaltyId { get; set; } = -1;
    public int LoyaltyPointPrice { get; set; } = -1;
}
