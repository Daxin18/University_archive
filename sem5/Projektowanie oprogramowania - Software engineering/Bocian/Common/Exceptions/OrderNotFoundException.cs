namespace Bocian.Common.Exceptions;

public sealed class OrderNotFoundException : ShopException
{
    public OrderNotFoundException() : base(StatusCodes.Status404NotFound) { }
}
