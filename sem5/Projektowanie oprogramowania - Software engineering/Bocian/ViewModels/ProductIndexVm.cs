using Microsoft.AspNetCore.Mvc.Rendering;
using Bocian.Models;

namespace Bocian.ViewModels;

public sealed class ProductIndexVm
{
    public int? SelectedCategoryId { get; set; } = null;
    public List<SelectListItem> Categories { get; set; } = new List<SelectListItem>();
    public List<(Product, bool, Image, Category)> Products { get; set; } = new List<(Product, bool, Image, Category)>();
}
