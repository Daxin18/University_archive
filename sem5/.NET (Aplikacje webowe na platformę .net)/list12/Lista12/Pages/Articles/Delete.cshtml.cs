using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Lista12.Data;
using Lista12.Models;
using System.IO;
using Microsoft.AspNetCore.Hosting;

namespace Lista12.Pages.Articles
{
    public class DeleteModel : PageModel
    {
        private readonly MyDbContext _context;
        private readonly IWebHostEnvironment _hostEnvironment;

        public DeleteModel(MyDbContext context, IWebHostEnvironment hostEnvironment)
        {
            _context = context;
            _hostEnvironment = hostEnvironment;
        }

        [BindProperty]
        public Article Article { get; set; }

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            Article = await _context.Article
                .Include(a => a.Category).FirstOrDefaultAsync(m => m.Id == id);

            if (Article == null)
            {
                return NotFound();
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            var article = await _context.Article.FindAsync(id);

            if (article.Image != null)
            {
                string articlePath = article.Image.Substring(8);
                string filePath = Path.Combine(_hostEnvironment.WebRootPath, "upload", articlePath);
                var image = new FileInfo(filePath);


                if (image.Exists)
                {
                    image.Delete();
                }
            }

            _context.Article.Remove(article);
            await _context.SaveChangesAsync();
            return RedirectToPage(nameof(Index));
        }
    }
}
