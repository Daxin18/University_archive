using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Diagnostics;
using Bocian.Common;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[Index(nameof(Email), IsUnique = true)]
public abstract class Account
{
    public int Id { get; init; }

    [EmailAddress]
    [DataType(DataType.EmailAddress)]
    [MaxLength(255)]
    public required string Email { get; set; }

    [MaxLength(255)]
    public required string FirstName { get; set; }

    [MaxLength(255)]
    public required string LastName { get; set; }

    [NotMapped]
    public string FullName => $"{FirstName} {LastName}";

    [NotMapped]
    public string RoleName =>
        this switch
        {
            Client => Roles.Client,
            Courier => Roles.Courier,
            Manager => Roles.Manager,
            Employee => Roles.Employee,
            _ => throw new UnreachableException()
        };
}
