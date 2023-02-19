using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Bocian.Models;

public sealed class Order
{
    public int Id { get; init; }

    public required OrderStatus Status { get; set; }

    [DataType(DataType.DateTime)]
    public required DateTimeOffset OrderedAt { get; init; }

    public required int ClientId { get; init; }
    public Client? Client { get; init; }

    public required int AddressId { get; init; }
    public Address? Address { get; init; }

    public required int PaymentId { get; init; }
    public Payment? Payment { get; init; }

    public required int CartId { get; init; }
    public Cart? Cart { get; init; }

    public int? EmployeeId { get; set; }
    public Employee? Employee { get; set; }

    public Delivery? Delivery { get; set; }
    public Complaint? Complaint { get; set; }

    [NotMapped]
    public int GrantedPoints => (int)(Cart?.TotalPrice * 100 ?? 0);

    public bool IsAccessibleByEmployee(int employeeId) =>
        Status == OrderStatus.Pending
        || (Status == OrderStatus.Confirmed && EmployeeId == employeeId);

    public bool IsAccessibleByCourier(int courierId) =>
        Status == OrderStatus.Packed
        || (Status == OrderStatus.InDelivery && Delivery?.CourierId == courierId);
}
