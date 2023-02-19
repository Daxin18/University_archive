using System.Security.Claims;
using Bocian.Common;
using Bocian.Data;
using Bocian.ViewModels;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

internal sealed class AuthService : IAuthService
{
    private readonly ShopDbContext _dbContext;

    public AuthService(ShopDbContext dbContext) => _dbContext = dbContext;

    public async Task<ClaimsIdentity?> AuthenticateUser(string email, string password)
    {
        var account = await _dbContext.Accounts.FirstOrDefaultAsync(a => a.Email == email);

        if (account is null)
            return null;

        return new ClaimsIdentity(
            new List<Claim>
            {
                new(ClaimTypes.Name, account.Id.ToString()),
                new(ClaimTypes.Email, email),
                new(ClaimTypes.GivenName, account.FullName),
                new(ClaimTypes.Role, account.RoleName)
            },
            CookieAuthenticationDefaults.AuthenticationScheme
        );
    }

    public async Task<AccountDetailsVm?> GetAccountDetails(string email)
    {
        var account = await _dbContext.Accounts.FirstOrDefaultAsync(a => a.Email == email);

        if (account is null)
            return null;

        return new AccountDetailsVm
        {
            Email = email,
            FullName = account.FullName,
            TranslatedRoleName = I18N.TranslateRoleName(account.RoleName)
        };
    }
}
