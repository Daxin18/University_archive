using System.Diagnostics;

namespace Bocian.Common;

internal static class I18N
{
    public static string TranslateRoleName(string roleName) =>
        roleName switch
        {
            Roles.Client => "Klient",
            Roles.Courier => "Kurier",
            Roles.Manager => "Kierownik",
            Roles.Employee => "Pracownik",
            _ => throw new UnreachableException()
        };
}
