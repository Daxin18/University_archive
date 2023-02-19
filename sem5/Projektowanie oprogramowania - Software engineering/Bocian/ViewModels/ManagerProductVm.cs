namespace Bocian.ViewModels
{
	public class ManagerProductVm
	{
		public int Id { get; set; }
		public string Name { get; set; }
		public string Description { get; set; }
		public decimal Price { get; set; }
		public bool OnlyForAdults { get; set; }
		public double Weight { get; set; }
		public int CategoryId { get; set; }
		public string ImageUrl { get; set; }
		public int shopId { get; set; }
		public int Quantity { get; set; }
	}
}
