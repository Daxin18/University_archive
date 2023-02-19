using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc.Rendering;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Lista10.Models
{
    public class Cart
    {
        public List<SelectListItem> articleCookies;
        public List<(Article article, string quantity)> articles;
    }
}
