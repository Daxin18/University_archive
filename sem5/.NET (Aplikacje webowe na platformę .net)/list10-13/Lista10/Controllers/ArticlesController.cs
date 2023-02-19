using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Lista10.Data;
using Lista10.Models;
using System.IO;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Authorization;

namespace Lista10.Controllers
{
    public class ArticlesController : Controller
    {
        private readonly MyDbContext _context;
        private readonly IWebHostEnvironment _hostEnvironment;
        private const string DEFAULT = "/image/default.jpg";

        public ArticlesController(MyDbContext context, IWebHostEnvironment hostEnvironment )
        {
            _context = context;
            _hostEnvironment = hostEnvironment;
        }

        // GET: Articles
        public async Task<IActionResult> Index()
        {
            var myDbContext = _context.Article.Include(a => a.Category);
            return View(await myDbContext.ToListAsync());
        }

        // GET: Articles/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var article = await _context.Article
                .Include(a => a.Category)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (article == null)
            {
                return NotFound();
            }

            return View(article);
        }

        // GET: Articles/Create
        [Authorize(Roles = "Admin")]
        public IActionResult Create()
        {
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name");
            return View();
        }

        // POST: Articles/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> Create([Bind("Id,Name,Price,Photo,Image,CategoryId")] Article article)
        {
            if (ModelState.IsValid)
            {
                string newImageFile = Path.Combine(_hostEnvironment.WebRootPath, DEFAULT);
                if (article.Photo != null)
                {
                    string uploadPath = Path.Combine(_hostEnvironment.WebRootPath, "upload");
                    newImageFile = Guid.NewGuid().ToString() + "_" + article.Photo.FileName;

                    var fileStream = new FileStream(Path.Combine(uploadPath, newImageFile), FileMode.Create);
                    article.Photo.CopyTo(fileStream);
                    fileStream.Close();
                    newImageFile = "/upload/" + newImageFile;
                }

                Article newArticle = new Article()
                {
                    Name = article.Name,
                    Price = article.Price,
                    CategoryId = article.CategoryId,
                    Image = newImageFile
                };

                _context.Add(newArticle);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name", article.CategoryId);
            return View(article);
        }

        // GET: Articles/Edit/5
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var article = await _context.Article.FindAsync(id);
            if (article == null)
            {
                return NotFound();
            }
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name", article.CategoryId);
            return View(article);
        }

        // POST: Articles/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Name,Price,Image,CategoryId")] Article article)
        {
            if (id != article.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(article);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ArticleExists(article.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["CategoryId"] = new SelectList(_context.Category, "Id", "Name", article.CategoryId);
            return View(article);
        }

        // GET: Articles/Delete/5
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var article = await _context.Article
                .Include(a => a.Category)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (article == null)
            {
                return NotFound();
            }

            return View(article);
        }

        // POST: Articles/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> DeleteConfirmed(int id)
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
            return RedirectToAction(nameof(Index));
        }

        private bool ArticleExists(int id)
        {
            return _context.Article.Any(e => e.Id == id);
        }
    }
}
