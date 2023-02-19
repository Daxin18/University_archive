using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lista9.DataContext;
using Lista9.Models;

namespace Lista9.Controllers
{
    public class ArticleController : Controller
    {
        private IArticleContext _articleContext;

        public ArticleController(IArticleContext articleContext)
        {
            _articleContext = articleContext;
        }

        // GET: HomeController1
        public ActionResult Index()
        {
            return View(_articleContext.GetArticles());
        }

        // GET: HomeController1/Details/5
        public ActionResult Details(int id)
        {
            Article article = _articleContext.GetArticle(id);
            if (!(article is null))
                return View(article);
            return View();
        }

        // GET: HomeController1/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: HomeController1/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Article article)
        {
            try
            {
                if (ModelState.IsValid)
                    _articleContext.AddArticle(article);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: HomeController1/Edit/5
        public ActionResult Edit(int id)
        {
            Article article = _articleContext.GetArticle(id);
            if (!(article is null))
                return View(article);
            return View();
        }

        // POST: HomeController1/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(int id, Article article)
        {
            try
            {
                if (ModelState.IsValid)
                    _articleContext.UpdateArticle(article);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: HomeController1/Delete/5
        public ActionResult Delete(int id)
        {
            Article article = _articleContext.GetArticle(id);
            if (!(article is null))
                return View(article);
            return View();
        }

        // POST: HomeController1/Delete/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Delete(int id, Article article)
        {
            try
            {
                if (ModelState.IsValid)
                    _articleContext.RemoveArticle(article.Id);
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }
    }
}
