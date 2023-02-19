using System.Security.Claims;
using Bocian.ViewModels;

namespace Bocian.Services;

public interface IAuthService
{
    public Task<ClaimsIdentity?> AuthenticateUser(string email, string password);

    public Task<AccountDetailsVm?> GetAccountDetails(string email);
}
