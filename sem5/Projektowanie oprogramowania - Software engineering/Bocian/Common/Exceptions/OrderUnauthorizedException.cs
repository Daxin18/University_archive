namespace Bocian.Common.Exceptions;

public sealed class OrderUnauthorizedException : ShopException
{
    public OrderUnauthorizedException() : base(StatusCodes.Status401Unauthorized) { }
}
