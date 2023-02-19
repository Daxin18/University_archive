using AutoMapper;
using Bocian.Models;
using Bocian.ViewModels;
using JetBrains.Annotations;

namespace Bocian.Common.MapperProfiles;

[UsedImplicitly]
public sealed class DeliveryProfile : Profile
{
    public DeliveryProfile()
    {
        CreateMap<Order, DeliveryIndexOrderVm>();
        CreateMap<CartItem, DeliveryDetailsCartItemVm>();
        CreateMap<Order, DeliveryDetailsVm>();
    }
}
