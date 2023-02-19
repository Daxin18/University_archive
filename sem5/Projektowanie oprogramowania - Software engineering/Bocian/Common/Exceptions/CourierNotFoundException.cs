namespace Bocian.Common.Exceptions;

public sealed class CourierNotFoundException : ShopException
{
    public CourierNotFoundException() : base(StatusCodes.Status404NotFound) { }
}
