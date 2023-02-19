using Bocian.Common;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Bocian.Controllers;

[Authorize(Roles = Roles.Client)]
[Route("favorites")]
public sealed class FavoriteController : Controller { }
