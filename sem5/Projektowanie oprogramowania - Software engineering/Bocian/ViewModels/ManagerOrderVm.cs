namespace Bocian.ViewModels;

public sealed class ManagerOrderVm
{
	public int Id { get; set; }
	public string Status { get; set; }
	public string? ClientNumber { get; set; }
	public AddressVm Address { get; set; }
	public decimal TotalPrice { get; set; }
	public DateTimeOffset OrderedAt { get; set; }
	public List<SimpleProductVm> Products { get; set; }
}

