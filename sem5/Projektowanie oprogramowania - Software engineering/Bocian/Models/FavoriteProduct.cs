using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Models;

[PrimaryKey(nameof(ClientId), nameof(ProductId))]
public sealed class FavoriteProduct
{
    [DataType(DataType.DateTime)]
    public required DateTimeOffset LikedAt { get; init; }

    public required int ClientId { get; init; }
    public Client? Client { get; init; }

    public required int ProductId { get; init; }
    public Product? Product { get; init; }
}
