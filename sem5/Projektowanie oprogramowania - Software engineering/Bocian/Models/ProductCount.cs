namespace Bocian.Models;

public class ProductCount
{
	public int Id { get; init; }
	public int ProductId { get; init; }
	public int ShopId { get; init; }
	public int Quantity { get; set; }
}

