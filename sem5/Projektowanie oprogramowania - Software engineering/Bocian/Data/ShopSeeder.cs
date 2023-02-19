using Bocian.Models;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Data;

internal static class ShopSeeder
{
	public static void SeedShops(this ModelBuilder modelBuilder)
	{
		modelBuilder
			.Entity<Shop>()
			.HasData(
				new Shop
				{
					Id = 1,
					ManagerId = 3,
					AddressId = 1,
					PhoneNumber = "123 123 123"
				},
				new Shop
				{
					Id = 1001,
					AddressId = 1001,
					ManagerId = 3,
					PhoneNumber = "61 856 37 00"
				},
				new Shop
				{
					Id = 1002,
					AddressId = 1002,
					ManagerId = 3,
					PhoneNumber = "61 856 37 01"
				},
				new Shop
				{
					Id = 1003,
					AddressId = 1003,
					ManagerId = 3,
					PhoneNumber = "61 856 48 22"
				},
				new Shop
				{
					Id = 1004,
					AddressId = 1004,
					ManagerId = 3,
					PhoneNumber = "61 658 27 10"
				}
			);
	}
}
