using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class Category
{
    public int Id { get; init; }

    [MaxLength(255)]
    public required string Name { get; set; }

    public ICollection<Product> Products { get; set; } = new List<Product>();
}
