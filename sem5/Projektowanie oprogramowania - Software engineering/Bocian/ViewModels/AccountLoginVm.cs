using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace Bocian.ViewModels;

public sealed class AccountLoginVm
{
    [EmailAddress(ErrorMessage = "{0} musi być poprawnym adresem.")]
    [DataType(DataType.EmailAddress)]
    [MaxLength(255, ErrorMessage = "{0} musi być krótszy niż {1}.")]
    [Required(ErrorMessage = "{0} jest wymagany.")]
    public required string Email { get; set; }

    [PasswordPropertyText]
    [DataType(DataType.Password)]
    [Display(Name = "Hasło")]
    [Required(ErrorMessage = "{0} jest wymagane.")]
    public required string Password { get; set; }
}
