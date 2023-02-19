using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

public sealed class Image
{
    public int Id { get; init; }

    [Url]
    [DataType(DataType.ImageUrl)]
    [MaxLength(1000)]
    public required string Url { get; set; }
}
