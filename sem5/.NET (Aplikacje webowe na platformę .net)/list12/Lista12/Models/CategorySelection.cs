using Microsoft.AspNetCore.Mvc.Rendering;
using System.Collections.Generic;

namespace Lista12.Models
{
    public class CategorySelection
    {
        public int selectedCategoryId { get; set; }
        public List<SelectListItem> categories { get; set; } = new List<SelectListItem>();
        public List<Article> articles { get; set; } = new List<Article>();
    }
}
