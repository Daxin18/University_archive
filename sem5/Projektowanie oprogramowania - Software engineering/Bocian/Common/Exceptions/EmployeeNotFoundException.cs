namespace Bocian.Common.Exceptions;

public sealed class EmployeeNotFoundException : ShopException
{
    public EmployeeNotFoundException() : base(StatusCodes.Status404NotFound) { }
}
