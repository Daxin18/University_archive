using System.ComponentModel.DataAnnotations;

namespace Bocian.ViewModels;

public sealed class AccountDetailsVm
{
    [DataType(DataType.EmailAddress)]
    public required string Email { get; init; }

    [Display(Name = "Nazwa")]
    public required string FullName { get; init; }

    [Display(Name = "Rola")]
    public required string TranslatedRoleName { get; init; }
}
