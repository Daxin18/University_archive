using Bocian.Common;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Client)]
[Route("cart")]
public sealed class CartController : Controller { }
