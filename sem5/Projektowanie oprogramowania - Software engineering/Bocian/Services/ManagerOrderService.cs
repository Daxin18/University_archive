using Bocian.Data;
using Bocian.ViewModels;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

public class ManagerOrderService : IManagerOrderService
{
	private readonly ShopDbContext _context;

	public ManagerOrderService(ShopDbContext context) => _context = context;

	public List<ManagerOrderVm> AllOrdersForShop(int shopId)
	{
		var orders = _context.Orders
			.Include(o => o.Client)
			.Include(o => o.Delivery)
			.Include(o => o.Employee)
			.Include(o => o.Address)
			.Include(o => o.Cart)
			.ThenInclude(c => c!.Items)
			.ThenInclude(ci => ci.Product)
			.Where(o => o.Employee.ShopId == shopId)
			.ToList()
			.Select(o => new ManagerOrderVm
			{	
				Id = o.Id,
				Status = o.Status.ToString(),
				Address = new AddressVm
				{
					Id = o.Address!.Id,
					ApartmentNumber = o.Address!.ApartmentNumber,
					City = o.Address!.City,
					HouseNumber = o.Address!.HouseNumber,
					PostalCode = o.Address!.PostalCode,
					Street = o.Address!.Street
				},
				ClientNumber = o.Client!.PhoneNumber,
				TotalPrice = o.Cart!.TotalPrice,
				OrderedAt = o.OrderedAt,
				Products = o.Cart.Items.Select(ci => new SimpleProductVm
				{
					Id = ci.Product!.Id,
					Name = ci.Product!.Name,
					Price = ci.Product!.Price,
					Quantity = ci.ItemCount

				}).ToList()
			});

		return orders.ToList();
	}

	public List<ManagerOrderVm> AllDoneOrdersForShop(int shopId)
	{
		return AllOrdersForShop(shopId)
			.Where(o => o.Status is "Completed" or "Disputed")
			.ToList();
	}

	public List<ManagerOrderVm> AllInProgressOrdersForShop(int shopId)
	{
		return AllOrdersForShop(shopId)
			.Where(o => o.Status is "Confirmed" or "InDelivery" or "Packed")
			.ToList();
	}

	public List<ManagerOrderVm> AllCanceledOrdersForShop(int shopId)
	{
		return AllOrdersForShop(shopId)
			.Where(o => o.Status is "Canceled")
			.ToList();
	}

	public bool DeleteOrder(int orderId)
	{
		var order = _context.Orders.Find(orderId);

		if (order is null)
			return false;

		_context.Orders.Remove(order);
		_context.SaveChanges();
		return true;
	}
}

