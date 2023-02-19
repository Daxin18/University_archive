using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[Index(nameof(Name), IsUnique = true)]
public sealed class TransportationType
{
    public int Id { get; init; }

    [StringLength(256, MinimumLength = 1)]
    public required string Name { get; init; }
}
