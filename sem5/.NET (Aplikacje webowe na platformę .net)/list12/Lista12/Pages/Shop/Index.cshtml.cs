using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Lista12.Data;
using Lista12.Models;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace Lista12.Pages.Shop
{
    public class IndexModel : PageModel
    {
        private readonly MyDbContext _context;
        
        [BindProperty(SupportsGet = true)]
        public int selectedCategoryId { get; set; }
        [BindProperty]
        public List<SelectListItem> categories { get; set; } = new List<SelectListItem>();
        [BindProperty]
        public List<Article> articles { get; set; } = new List<Article>();

        public IndexModel(MyDbContext context)
        {
            _context = context;
        }

        public IList<Article> Article { get; set; }

        public async Task<IActionResult> OnGetAsync()
        {
            categories = _context.Category.ToList().Select(x => new SelectListItem()
            {
                Text = x.Name,
                Value = x.Id.ToString()
            }).ToList();

            articles = _context.Article.ToList().FindAll(x => x.CategoryId == selectedCategoryId);

            return Page();
        }
    }
}

