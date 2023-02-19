namespace Bocian.ViewModels;
public sealed class AddressVm
{
	public int Id { get; init; }
	public string PostalCode { get; init; }
	public string City { get; init; }
	public string Street { get; init; }
	public int HouseNumber { get; init; }
	public int? ApartmentNumber { get; init; }

	public string FullNumber =>
		ApartmentNumber is { } apartmentNumber
			? $"{HouseNumber}/{apartmentNumber}"
			: HouseNumber.ToString();


	public string Summary => $"ul. {Street} {FullNumber}, {PostalCode} {City}";
}

