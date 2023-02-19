using System.Security.Claims;

namespace Bocian.Common;

internal static class ClaimsPrincipalExtensions
{
    public static bool IsLoggedIn(this ClaimsPrincipal user) =>
        user.Identity?.IsAuthenticated == true;

    public static int Id(this ClaimsPrincipal user) =>
        int.Parse(ExtractClaim(user, ClaimTypes.Name));

    public static string Email(this ClaimsPrincipal user) => ExtractClaim(user, ClaimTypes.Email);

    public static string FullName(this ClaimsPrincipal user) =>
        ExtractClaim(user, ClaimTypes.GivenName);

    public static string TranslatedRoleName(this ClaimsPrincipal user) =>
        I18N.TranslateRoleName(ExtractClaim(user, ClaimTypes.Role));

    private static string ExtractClaim(ClaimsPrincipal user, string claimType) =>
        user.Claims.Where(c => c.Type == claimType).Select(c => c.Value).Single();
}
