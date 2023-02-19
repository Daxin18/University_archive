using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Lista10.Models;
using Lista10.Data;
using Microsoft.AspNetCore.Mvc.Rendering;
using System.Linq;
using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Authorization;
using Lista10.ViewModels;

namespace Lista10.Controllers
{
    public class ShopController : Controller
    {
        private readonly MyDbContext _context;
        private readonly IWebHostEnvironment _hostEnvironment;

        public ShopController(MyDbContext context, IWebHostEnvironment hostEnvironment)
        {
            _context = context;
            _hostEnvironment = hostEnvironment;
        }

        public ActionResult Index(CategorySelection categoryViewModel)
        {
            if (ModelState.IsValid)
            {
                categoryViewModel.categories = _context.Category.ToList().Select(x => new SelectListItem()
                {
                    Text = x.Name,
                    Value = x.Id.ToString()
                }).ToList();

                var articlesFromCategory = _context.Article.Where(x => x.CategoryId == categoryViewModel.selectedCategoryId).ToList();
                categoryViewModel.articles = articlesFromCategory;

            }
            return View(categoryViewModel);
        }

        //TODO: admin nie może kliknąć (done?)
        [Authorize(Policy = "NotForAdmin")]
        public ActionResult Cart(Cart cart, string returnView)
        {
            if (ModelState.IsValid)
            {
                cart.articleCookies = Request.Cookies.Select(x => new SelectListItem()
                {
                    Text = x.Key,
                    Value = x.Value
                }).ToList();

                var tmp = new List<SelectListItem>();

                foreach (var cookie in cart.articleCookies)
                {
                    if (cookie.Text.Contains("article"))
                    {
                        cookie.Text = cookie.Text.Split("article")[1];
                        tmp.Add(cookie);
                    }
                }

                cart.articleCookies = tmp;

                var articlesFromCookies = new List<(Article, string)>();

                foreach (var article in _context.Article.ToList())
                {
                    foreach (var cookieArticle in cart.articleCookies)
                    {
                        if (article.Id.ToString().Equals(cookieArticle.Text))
                        {
                            articlesFromCookies.Add((article, cookieArticle.Value));
                            break;
                        }
                    }
                }

                cart.articles = articlesFromCookies;

                if (cart.articleCookies.Count == 0)
                {
                    ViewBag.CartMessageClass = "cart-empty";
                    ViewBag.CartArticlePriceClass = "cart-not-empty"; //aka invisible (display: none)
                }
                else
                {
                    ViewBag.CartMessageClass = "cart-not-empty";
                    ViewBag.CartArticlePriceClass = "";
                    double sum = 0;

                    foreach (var (article, quantity) in cart.articles)
                    {
                        sum += (article.Price * Int32.Parse(quantity));
                    }

                    ViewBag.CartArticlePrice = sum;
                }
            }

            if (returnView == "OrderData")
            {
                ViewBag.CART = cart;
                return RedirectToAction(nameof(OrderData), cart);
            }
            else
            {
                return View(cart);
            }
        }

        [Authorize(Policy = "NotForAdmin")]
        public ActionResult AddOne(int? id)
        {
            string cookieName = "article" + id.ToString();
            var cookieValue = Request.Cookies[cookieName];

            SetCookie(cookieName, (Int32.Parse(cookieValue) + 1).ToString(), 7);

            return RedirectToAction("Cart");
        }

        [Authorize(Policy = "NotForAdmin")]
        public ActionResult RemoveOne(int? id)
        {
            string cookieName = "article" + id.ToString();
            var cookieValue = Request.Cookies[cookieName];

            int newQuantity = (Int32.Parse(cookieValue) - 1);

            if (newQuantity > 0)
            {
                SetCookie(cookieName, newQuantity.ToString(), 7);
            }
            else
            {
                SetCookie(cookieName, "", -1);
            }

            return RedirectToAction("Cart");
        }

        [Authorize(Policy = "NotForAdmin")]
        public ActionResult AddToCart(int? id)
        {
            bool cookieExists = false;
            string cookieName = "article" + id.ToString();

            foreach (var cookie in Request.Cookies)
            {
                if (cookie.Key.Equals(cookieName))
                {
                    SetCookie(cookie.Key, (Int32.Parse(cookie.Value) + 1).ToString(), 7);
                    cookieExists = true;
                    break;
                }
            }
            if (!cookieExists)
            {
                SetCookie(cookieName, 1.ToString(), 7);
            }

            return RedirectToAction("Index");
        }

        public void SetCookie(string key, string value, int? numberOfDays = null)
        {
            CookieOptions option = new CookieOptions();
            if (numberOfDays.HasValue)
            {
                option.Expires = DateTime.Now.AddDays(numberOfDays.Value);
            }
            Response.Cookies.Append(key, value, option);
        }

        [Authorize]
        [Authorize(Policy = "NotForAdmin")]
        public ActionResult OrderData(Cart cart)
        {
            if (ModelState.IsValid)
            {
                cart.articleCookies = Request.Cookies.Select(x => new SelectListItem()
                {
                    Text = x.Key,
                    Value = x.Value
                }).ToList();

                var tmp = new List<SelectListItem>();

                foreach (var cookie in cart.articleCookies)
                {
                    if (cookie.Text.Contains("article"))
                    {
                        cookie.Text = cookie.Text.Split("article")[1];
                        tmp.Add(cookie);
                    }
                }

                cart.articleCookies = tmp;

                var articlesFromCookies = new List<(Article, string)>();

                foreach (var article in _context.Article.ToList())
                {
                    foreach (var cookieArticle in cart.articleCookies)
                    {
                        if (article.Id.ToString().Equals(cookieArticle.Text))
                        {
                            articlesFromCookies.Add((article, cookieArticle.Value));
                            break;
                        }
                    }
                }

                cart.articles = articlesFromCookies;

                if (cart.articleCookies.Count == 0)
                {
                    ViewBag.CartMessageClass = "cart-empty";
                    ViewBag.CartArticlePriceClass = "cart-not-empty"; //aka invisible (display: none)
                }
                else
                {
                    ViewBag.CartMessageClass = "cart-not-empty";
                    ViewBag.CartArticlePriceClass = "";
                    double sum = 0;

                    foreach (var (article, quantity) in cart.articles)
                    {
                        sum += (article.Price * Int32.Parse(quantity));
                    }

                    ViewBag.CartArticlePrice = sum;
                }
            }

            OrderDataViewModel viewModel = new()
            {
                Articles = cart.articles,
                Name = "",
                Surname = "",
                Address = "",
                Payment_method = ""
            };

            List<SelectListItem> temp = new();

            temp.Add(new SelectListItem()
            {
                Text = "Karta",
                Value = "Karta"
            });
            temp.Add(new SelectListItem()
            {
                Text = "Gotówka",
                Value = "Gotówka"
            });

            ViewBag.PaymentMethods = temp;

            return View(viewModel);
        }
    
        [HttpPost]
        [Authorize]
        [Authorize(Policy = "NotForAdmin")]
        public ActionResult AddOrder([Bind("Articles,Name,Surname,Address,Payment_method")]OrderDataViewModel viewModel)
        {
            viewModel.Articles = GetNonNullCart().articles;
            foreach ((Article article, string quantity) in viewModel.Articles)
            {
                RemoveArticleCookie(article.Id);
            }
            return View(viewModel);
        }

        private Cart GetNonNullCart()
        {
            Cart cart = new Cart();
            cart.articleCookies = Request.Cookies.Select(x => new SelectListItem()
            {
                Text = x.Key,
                Value = x.Value
            }).ToList();

            var tmp = new List<SelectListItem>();

            foreach (var cookie in cart.articleCookies)
            {
                if (cookie.Text.Contains("article"))
                {
                    cookie.Text = cookie.Text.Split("article")[1];
                    tmp.Add(cookie);
                }
            }

            cart.articleCookies = tmp;

            var articlesFromCookies = new List<(Article, string)>();

            foreach (var article in _context.Article.ToList())
            {
                foreach (var cookieArticle in cart.articleCookies)
                {
                    if (article.Id.ToString().Equals(cookieArticle.Text))
                    {
                        articlesFromCookies.Add((article, cookieArticle.Value));
                        break;
                    }
                }
            }

            cart.articles = articlesFromCookies;

            if (cart.articleCookies.Count == 0)
            {
                ViewBag.CartMessageClass = "cart-empty";
                ViewBag.CartArticlePriceClass = "cart-not-empty"; //aka invisible (display: none)
            }
            else
            {
                ViewBag.CartMessageClass = "cart-not-empty";
                ViewBag.CartArticlePriceClass = "";
                double sum = 0;

                foreach (var (article, quantity) in cart.articles)
                {
                    sum += (article.Price * Int32.Parse(quantity));
                }

                ViewBag.CartArticlePrice = sum;
            }
            return cart;
        }
        private void RemoveArticleCookie(int articleId)
        {
            string cookieName = "article" + articleId.ToString();

            SetCookie(cookieName, "", -1);
        }
    }
}
