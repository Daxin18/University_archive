namespace Bocian.Common.Exceptions;

public sealed class OrderInvalidStatusException : ShopException
{
    public OrderInvalidStatusException() : base(StatusCodes.Status400BadRequest) { }
}
