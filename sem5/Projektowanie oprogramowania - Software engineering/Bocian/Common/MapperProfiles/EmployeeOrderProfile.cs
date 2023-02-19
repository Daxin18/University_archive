using AutoMapper;
using Bocian.Models;
using Bocian.ViewModels;
using JetBrains.Annotations;

namespace Bocian.Common.MapperProfiles;

[UsedImplicitly]
public sealed class EmployeeOrderProfile : Profile
{
    public EmployeeOrderProfile()
    {
        CreateMap<Order, EmployeeOrderIndexOrderVm>();
        CreateMap<CartItem, EmployeeOrderDetailsCartItemVm>();
        CreateMap<Order, EmployeeOrderDetailsVm>();
    }
}
