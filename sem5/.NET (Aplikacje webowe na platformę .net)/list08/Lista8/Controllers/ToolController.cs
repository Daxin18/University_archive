using Lista8.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace Lista8.Controllers
{
    public class ToolController : Controller
    {
        private readonly ILogger<ToolController> _logger;

        public ToolController(ILogger<ToolController> logger)
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

        [Route("Tool/Solve/{a:double}/{b:double}/{c:double}")]
        public IActionResult SolveQuadraticEquation(int a, int b, int c)
        {
            int solutionCount = -1;
            double[] solution = SolveEquation(a, b, c, ref solutionCount);
            string viewMessage = "", viewSecondaryMessage = "";
            string viewClass = "";

            switch (solutionCount)
            {
                case 0:
                    viewMessage = "No solutions exist";
                    viewClass = "no";
                    break;
                case 1:
                    viewMessage = $"Equation ({a})x^2 + ({b})x + ({c}) = 0 has a solution of x = {Math.Round(solution[0],5)}";
                    viewClass = "one";
                    break;
                case 2:
                    viewMessage = $"Equation ({a})x^2 + ({b})x + ({c}) = 0 has {solutionCount} solutions:";
                    viewSecondaryMessage += $"x = {Math.Round(solution[0], 5)} and x = {Math.Round(solution[1], 5)}";
                    viewClass = "two";
                    break;
                case 3:
                    viewMessage = "x can be any number";
                    viewClass = "any-number";
                    break;
            }

            ViewBag.Message = viewMessage;
            ViewBag.SecondaryMessage = viewSecondaryMessage;
            ViewBag.Class = viewClass;

            return View("SolveQuadraticEquation");
        }

        public static double[] SolveEquation(double a, double b, double c, ref int solutionCount)
        {
            double[] results = new double[2];
            if (a == 0)
            {
                if (b != 0)
                {
                    solutionCount = 1;
                    results[0] = -c / b;
                }
                else if (c != 0)
                    solutionCount = 0;
                else
                    solutionCount = 3;
            }
            else
            {
                double delta = ((b * b) - (4 * a * c));
                if (delta < 0)
                {
                    solutionCount = 0;
                }
                else if (delta == 0)
                {
                    solutionCount = 1;
                    results[0] = (-b / (2 * a));
                }
                else if (delta > 0)
                {
                    solutionCount = 2;
                    results[0] = ((-b - Math.Sqrt(delta)) / (2 * a));
                    results[1] = ((-b + Math.Sqrt(delta)) / (2 * a));
                }
            }
            return results;
        }
    }
}
