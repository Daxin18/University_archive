namespace Bocian.Models;

public sealed class Manager : Employee
{
    public ICollection<Shop> Shops { get; set; } = new List<Shop>();
}
