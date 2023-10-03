package ru.gasworkers.dev.api.helper;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext filterContext) {
        Response response = filterContext.next(requestSpec, responseSpec);
        String methodName = extractMethodName();
        JsonLogger.log(methodName, response.asString());
        return response;
    }

    private String extractMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            if (element.getClassName().startsWith("ru.gasworkers.dev.api")) {
                return element.getMethodName();
            }
        }
        return "UnknownMethod";
    }
}
