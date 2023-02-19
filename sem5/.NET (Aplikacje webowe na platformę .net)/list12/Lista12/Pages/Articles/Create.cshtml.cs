using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Lista12.Data;
using Lista12.Models;
using System.IO;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;

namespace Lista12.Pages.Articles
{
    public class CreateModel : PageModel
    {
        private readonly MyDbContext _context;
        private readonly IWebHostEnvironment _hostEnvironment;
        private const string DEFAULT = "/image/default.jpg";

        public CreateModel(MyDbContext context, IWebHostEnvironment hostEnvironment)
        {
            _context = context;
            _hostEnvironment = hostEnvironment;
        }

        [BindProperty]
        public string Name { get; set; }

        [BindProperty]
        public double Price { get; set; }

        [BindProperty]
        public IFormFile Photo { get; set; }

        [BindProperty]
        public string? Image { get; set; }

        [BindProperty]
        public int CategoryId { get; set; }



        public IActionResult OnGet()
        {
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name");
            return Page();
        }

        [BindProperty]
        public Article Article { get; set; }

        // To protect from overposting attacks, see https://aka.ms/RazorPagesCRUD
        public async Task<IActionResult> OnPostAsync()
        {
            if (ModelState.IsValid)
            {
                string newImageFile = Path.Combine(_hostEnvironment.WebRootPath, DEFAULT);
                if (Photo != null)
                {
                    string uploadPath = Path.Combine(_hostEnvironment.WebRootPath, "upload");
                    newImageFile = Guid.NewGuid().ToString() + "_" + Photo.FileName;

                    var fileStream = new FileStream(Path.Combine(uploadPath, newImageFile), FileMode.Create);
                    Photo.CopyTo(fileStream);
                    fileStream.Close();
                    newImageFile = "/upload/" + newImageFile;
                }

                Article newArticle = new Article()
                {
                    Name = Name,
                    Price = Price,
                    CategoryId = CategoryId,
                    Image = newImageFile
                };

                _context.Add(newArticle);
                await _context.SaveChangesAsync();
                return RedirectToPage(nameof(Index));
            }
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name", CategoryId);
            return Page();
        }
    }
}
