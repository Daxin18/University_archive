using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public sealed class Shop
{
    public int Id { get; init; }

    public required int? ManagerId { get; set; }
    public Manager? Manager { get; set; }

    public required int AddressId { get; set; }
    public Address? Address { get; set; }

    [MaxLength(16)]
    public required string? PhoneNumber { get; init; }

	public ICollection<Employee> Employees { get; set; } = new List<Employee>();
}
