using Microsoft.AspNetCore.Mvc.Rendering;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class LoyaltyIndexVm
{
    public int? SelectedCategoryId { get; set; } = null;
    public List<SelectListItem> Categories { get; set; } = new List<SelectListItem>();
    public List<(LoyaltyScheme, Product, Image, Category)> LoyaltySchemes { get; set; } = new List<(LoyaltyScheme, Product, Image, Category)>();
}
