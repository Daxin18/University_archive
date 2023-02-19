namespace Bocian.Models;

public class Employee : Account
{
    public required int ShopId { get; init; }
    public Shop? Shop { get; init; }

    public ICollection<Order> Orders { get; set; } = new List<Order>();
}
