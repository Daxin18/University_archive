using Bocian.Data;
using Bocian.ViewModels;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

public class ShopService : IShopService
{
	private readonly ShopDbContext _context;

	public ShopService(ShopDbContext context) => _context = context;

	public List<ShopIndexVm> AllManagerShopIndexVms(int managerId)
	{
		var allManagerShopIndexVms = _context.Shops
			.Where(s => s.ManagerId == managerId)
			.Include(s => s.Address)
			.Include(s => s.Employees)
			.Select(s => new ShopIndexVm
			{
				Id = s.Id,
				Address = new AddressVm
				{
					Id = s.Address!.Id,
					ApartmentNumber = s.Address!.ApartmentNumber,
					City = s.Address!.City,
					HouseNumber = s.Address!.HouseNumber,
					PostalCode = s.Address!.PostalCode,
					Street = s.Address!.Street
				},
				NumberOfEmployees = s.Employees.Count,
				PhoneNumber = s.PhoneNumber!
			});

		return allManagerShopIndexVms.ToList();
	}

	public ShopIndexVm ShopIndexVm(int shopId)
	{
		var shop = _context.Shops
			.Where(s => s.Id == shopId)
			.Include(s => s.Address)
			.Include(s => s.Employees)
			.First();

		var shopIndexVm = new ShopIndexVm
		{
			Id = shop.Id,
			Address = new AddressVm
			{
				Id = shop.Address!.Id,
				ApartmentNumber = shop.Address!.ApartmentNumber,
				City = shop.Address!.City,
				HouseNumber = shop.Address!.HouseNumber,
				PostalCode = shop.Address!.PostalCode,
				Street = shop.Address!.Street
			},
			NumberOfEmployees = shop.Employees.Count,
			PhoneNumber = shop.PhoneNumber!
		};

		return shopIndexVm;
	}
}

