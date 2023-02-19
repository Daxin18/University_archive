using AutoMapper;
using Bocian.Common.Exceptions;
using Bocian.Data;
using Bocian.Models;
using Bocian.ViewModels;
using Microsoft.EntityFrameworkCore;

namespace Bocian.Services;

public class OrderService : IOrderService
{
    private readonly ShopDbContext _context;
    private readonly IMapper _mapper;

    public OrderService(ShopDbContext context, IMapper mapper)
    {
        _context = context;
        _mapper = mapper;
    }

    public Task<EmployeeOrderIndexVm> AllForEmployeeAsync(int employeeId)
    {
        var allOrders = OrdersWithEmployeeIncludes()
            .AsEnumerable()
            .Where(o => o.IsAccessibleByEmployee(employeeId))
            .ToList();

        var confirmedOrders = allOrders
            .Where(o => o.Status == OrderStatus.Confirmed)
            .Select(_mapper.Map<EmployeeOrderIndexOrderVm>);

        var pendingOrders = allOrders
            .Where(o => o.Status == OrderStatus.Pending)
            .Select(_mapper.Map<EmployeeOrderIndexOrderVm>);

        return Task.FromResult(
            new EmployeeOrderIndexVm
            {
                ConfirmedOrders = confirmedOrders,
                PendingOrders = pendingOrders
            }
        );
    }

    public async Task<EmployeeOrderDetailsVm> DetailsForEmployeeAsync(int orderId, int employeeId)
    {
        var order = await OrdersWithEmployeeIncludes().FirstOrDefaultAsync(o => o.Id == orderId);

        if (order is null)
            throw new OrderNotFoundException();

        if (!order.IsAccessibleByEmployee(employeeId))
            throw new OrderUnauthorizedException();

        return _mapper.Map<EmployeeOrderDetailsVm>(order);
    }

    public Task<DeliveryIndexVm> AllForCourierAsync(int courierId)
    {
        var allOrders = OrdersWithCourierIncludes()
            .AsEnumerable()
            .Where(o => o.IsAccessibleByCourier(courierId))
            .ToList();

        var inDeliveryOrders = allOrders
            .Where(o => o.Status == OrderStatus.InDelivery)
            .Select(_mapper.Map<DeliveryIndexOrderVm>);

        var packedOrders = allOrders
            .Where(o => o.Status == OrderStatus.Packed)
            .Select(_mapper.Map<DeliveryIndexOrderVm>);

        return Task.FromResult(
            new DeliveryIndexVm { InDeliveryOrders = inDeliveryOrders, PackedOrders = packedOrders }
        );
    }

    public async Task<DeliveryDetailsVm> DetailsForCourierAsync(int orderId, int courierId)
    {
        var order = await OrdersWithCourierIncludes().FirstOrDefaultAsync(o => o.Id == orderId);

        if (order is null)
            throw new OrderNotFoundException();

        if (!order.IsAccessibleByCourier(courierId))
            throw new OrderUnauthorizedException();

        return _mapper.Map<DeliveryDetailsVm>(order);
    }

    public async Task ConfirmAsync(int orderId, int employeeId)
    {
        var order = await GetOrderWithStatusAsync(orderId, OrderStatus.Pending);

        var employeeExists = await _context.Employees.AnyAsync(e => e.Id == employeeId);
        if (!employeeExists)
            throw new EmployeeNotFoundException();

        order.Status = OrderStatus.Confirmed;
        order.EmployeeId = employeeId;
        await _context.SaveChangesAsync();
    }

    public async Task CancelAsync(int orderId)
    {
        var order = await GetOrderWithStatusAsync(orderId, OrderStatus.Pending);
        order.Status = OrderStatus.Canceled;
        await _context.SaveChangesAsync();
    }

    public async Task PackAsync(int orderId, int employeeId)
    {
        var order = await GetOrderWithStatusAsync(orderId, OrderStatus.Confirmed);
        if (!order.IsAccessibleByEmployee(employeeId))
            throw new OrderUnauthorizedException();

        order.Status = OrderStatus.Packed;
        await _context.SaveChangesAsync();
    }

    public async Task DeliverAsync(int orderId, int courierId)
    {
        var order = await GetOrderWithStatusAsync(orderId, OrderStatus.Packed);

        var courierExists = await _context.Couriers.AnyAsync(c => c.Id == courierId);
        if (!courierExists)
            throw new CourierNotFoundException();

        var delivery = new Delivery
        {
            DeliveredAt = null,
            CourierId = courierId,
            OrderId = orderId
        };

        await _context.AddAsync(delivery);
        order.Status = OrderStatus.InDelivery;
        await _context.SaveChangesAsync();
    }

    public async Task CompleteAsync(int orderId, int courierId)
    {
        var order = await GetOrderWithStatusAsync(orderId, OrderStatus.InDelivery);
        if (!order.IsAccessibleByCourier(courierId))
            throw new OrderUnauthorizedException();

        order.Status = OrderStatus.Completed;
        order.Delivery!.DeliveredAt = DateTimeOffset.UtcNow;
        await _context.SaveChangesAsync();
    }

    private async Task<Order> GetOrderWithStatusAsync(int orderId, OrderStatus requiredStatus)
    {
        var order = await _context.Orders
            .Include(o => o.Delivery)
            .FirstOrDefaultAsync(o => o.Id == orderId);

        if (order is null)
            throw new OrderNotFoundException();

        if (order.Status != requiredStatus)
            throw new OrderInvalidStatusException();

        return order;
    }

    private IQueryable<Order> OrdersWithEmployeeIncludes() =>
        _context.Orders
            .Include(o => o.Address)
            .Include(o => o.Client)
            .Include(o => o.Cart)
            .ThenInclude(c => c!.Items)
            .ThenInclude(ci => ci.Product);

    private IQueryable<Order> OrdersWithCourierIncludes() =>
        _context.Orders
            .Include(o => o.Delivery)
            .ThenInclude(d => d!.Courier)
            .ThenInclude(c => c!.TransportationType)
            .Include(o => o.Employee)
            .ThenInclude(e => e!.Shop)
            .ThenInclude(s => s!.Address)
            .Include(o => o.Address)
            .Include(o => o.Cart)
            .ThenInclude(c => c!.Items)
            .ThenInclude(ci => ci.Product)
            .Include(o => o.Client);
}
