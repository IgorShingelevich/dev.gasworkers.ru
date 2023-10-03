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
        String uri = requestSpec.getURI();
        String responseBody = response.asString();
        logResponseToFile(uri, responseBody);
        return response;
    }

    private void logResponseToFile(String uri, String responseBody) {
        // Replace all non-alphanumeric characters with underscores
        String sanitizedUri = uri.replaceAll("\\W", "_");

        // Use JsonLogger to log the response to a file
        JsonLogger.log(sanitizedUri, responseBody);
    }
}
