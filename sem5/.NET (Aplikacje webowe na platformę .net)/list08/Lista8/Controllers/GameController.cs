using Lista8.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace Lista8.Controllers
{
    public class GameController : Controller
    {
        private readonly ILogger<GameController> _logger;

        public GameController(ILogger<GameController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }

        [Route("Set,{n:int}")]
        public IActionResult Set(int n)
        {
            if (n <= 0)
            {
                ViewBag.Message = "Given 'n' has to be > 0 (range is from 0 to n-1)";
                ViewBag.Class = "set-error";
            }
            else
            {
                HttpContext.Session.SetInt32("range", n);
                HttpContext.Session.SetInt32("randValue", 0);
                HttpContext.Session.SetInt32("counter", 0);
                HttpContext.Session.SetInt32("randValueDrawn", 0);
                HttpContext.Session.SetInt32("guessed", 0);
                HttpContext.Session.SetInt32("guessedAfter", 0);


                ViewBag.Message = $"Range set to [0,{n})";
                ViewBag.Class = "set";
            }

            ViewBag.Attempt = (int) HttpContext.Session.GetInt32("counter");
            ViewBag.Range = (int)HttpContext.Session.GetInt32("range");
            ViewBag.GuessedAfter = GuessedAfterMessage();
            return View("Game");
        }

        [Route("Draw")]
        public IActionResult Draw()
        {
            int range = HttpContext.Session.GetInt32("range") ?? 0;
        
            if (range == 0)
            {
                ViewBag.Message = "Range is set to 0, first set a proper range";
                ViewBag.Class = "draw-error";
            }
            else
            {
                Random r = new Random();
                HttpContext.Session.SetInt32("randValue", r.Next(range));
                HttpContext.Session.SetInt32("counter", 0);
                HttpContext.Session.SetInt32("randValueDrawn", 1);
                HttpContext.Session.SetInt32("guessed", 0);
                HttpContext.Session.SetInt32("guessedAfter", 0);

                ViewBag.Message = $"New random value chosen";
                ViewBag.Class = "draw";
            }

            ViewBag.Attempt = HttpContext.Session.GetInt32("counter") ?? 0;
            ViewBag.Range = range;
            ViewBag.GuessedAfter = GuessedAfterMessage();
            return View("Game");
        }

        [Route("Guess,{guess:int}")]
        public IActionResult Guess(int guess)
        {
            int range = HttpContext.Session.GetInt32("range") ?? 0;
            int randValue = HttpContext.Session.GetInt32("randValue") ?? 0;
            int counter = HttpContext.Session.GetInt32("counter") ?? 0;
            bool randValueDrawn = Convert.ToBoolean(HttpContext.Session.GetInt32("randValueDrawn") ?? 0);
            bool guessed = Convert.ToBoolean(HttpContext.Session.GetInt32("guessed") ?? 0);

            if (!randValueDrawn)
            {
                ViewBag.Message = $"Random value hasn't been drawn yet, you can't guess!";
                ViewBag.Class = "guess-not-drawn-error";
            }
            else if (guess < 0 || guess >= range)
            {
                ViewBag.Message = $"Guess '{guess}' is out of range [0 - {range})";
                ViewBag.Class = "guess-range-error";
            }
            else if (guess < randValue)
            {
                ViewBag.Message = $"Guess '{guess}' is LOWER than the random value";
                ViewBag.Class = "guess-lower";
                counter++;
            }
            else if (guess > randValue)
            {
                ViewBag.Message = $"Guess '{guess}' is HIGHER than the random value";
                ViewBag.Class = "guess-higher";
                counter++;
            }
            else
            {
                ViewBag.Message = $"Your guess '{guess}' is CORRECT";
                ViewBag.Class = "guess-correct";
                counter++;
                if (!guessed)
                {
                    HttpContext.Session.SetInt32("guessedAfter", counter);
                }
                HttpContext.Session.SetInt32("guessed", 1);
            }

            HttpContext.Session.SetInt32("counter", counter);
            ViewBag.Attempt = counter;
            ViewBag.Range = range;
            ViewBag.GuessedAfter = GuessedAfterMessage();
            return View("Game");
        }

        private string GuessedAfterMessage()
        {
            bool guessed = Convert.ToBoolean(HttpContext.Session.GetInt32("guessed") ?? 0);
            int randValue = HttpContext.Session.GetInt32("randValue") ?? 0;
            int guessedAfter = HttpContext.Session.GetInt32("guessedAfter") ?? 0;

            return guessed ? $"Random value is '{randValue}', you guessed it after {guessedAfter} attempts!" : "";
        }
    }
}
