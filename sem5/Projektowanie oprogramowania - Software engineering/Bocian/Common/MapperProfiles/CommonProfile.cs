using AutoMapper;
using JetBrains.Annotations;

namespace Bocian.Common.MapperProfiles;

[UsedImplicitly]
public sealed class CommonProfile : Profile
{
    public CommonProfile()
    {
        CreateMap<DateTimeOffset, DateTime>().ConvertUsing(src => src.LocalDateTime);
    }
}
