using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Mvc.ModelBinding;
using Microsoft.AspNetCore.Mvc.ViewFeatures;

namespace Bocian.Common.Exceptions;

public sealed class ExceptionInterceptor : IExceptionFilter
{
    public void OnException(ExceptionContext context)
    {
        if (context.Exception is ShopException se)
            context.Result = new ViewResult
            {
                ViewName = "Error",
                ViewData = new ViewDataDictionary(
                    new EmptyModelMetadataProvider(),
                    context.ModelState
                )
                {
                    Model = se.StatusCode
                }
            };
    }
}
