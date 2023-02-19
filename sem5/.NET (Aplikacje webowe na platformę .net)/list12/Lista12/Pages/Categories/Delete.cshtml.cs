using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Lista12.Data;
using Lista12.Models;

namespace Lista12.Pages.Categories
{
    public class DeleteModel : PageModel
    {
        private readonly Lista12.Data.MyDbContext _context;

        public DeleteModel(Lista12.Data.MyDbContext context)
        {
            _context = context;
        }

        [BindProperty]
        public Category Category { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Category = await _context.Category.FirstOrDefaultAsync(m => m.Id == id);

            if (Category == null)
            {
                return NotFound();
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            var category = await _context.Category.FindAsync(id);

            var articlesFromCategory = _context.Article.Where(x => x.CategoryId == category.Id).ToList().Count;

            if (articlesFromCategory == 0)
            {
                _context.Category.Remove(category);
                await _context.SaveChangesAsync();

            }
            else
            {
                ModelState.AddModelError("", $"Category {category.Name} cannot be deleted, bacause it has {articlesFromCategory} articles");
                return Page();
            }

            return RedirectToPage(nameof(Index));
        }
    }
}
