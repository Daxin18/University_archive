namespace Bocian.Common.Exceptions;

public abstract class ShopException : Exception
{
    public int StatusCode { get; private init; }

    protected ShopException(int statusCode) => StatusCode = statusCode;
}
