package ru.gasworkers.dev.api.helper;

import io.qameta.allure.Allure;
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
        // Capture and log request details
        String requestDetails = buildRequestDetails(requestSpec);
        Allure.addAttachment("API Request", "text/plain", requestDetails);

        // Capture and log response details
        String responseDetails = buildResponseDetails(response);
        Allure.addAttachment("API Response", "text/plain", responseDetails);
        return response;
    }

    private void logResponseToFile(String uri, String responseBody) {
        // Replace all non-alphanumeric characters with underscores
        String sanitizedUri = uri.replaceAll("\\W", "_");

        // Use JsonLogger to log the response to a file
        JsonLogger.log(sanitizedUri, responseBody);
    }

    private String buildRequestDetails(FilterableRequestSpecification requestSpec) {
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append("Method: ").append(requestSpec.getMethod()).append("\n");
        requestBuilder.append("URL: ").append(requestSpec.getURI()).append("\n");
        requestBuilder.append("Headers: ").append(requestSpec.getHeaders()).append("\n");
        requestBuilder.append("Query Params: ").append(requestSpec.getQueryParams()).append("\n");

        Object requestBody = requestSpec.getBody();
        if (requestBody != null) {
            if (requestBody instanceof String) {
                requestBuilder.append("Body: ").append(requestBody).append("\n");
            } else if (requestBody instanceof byte[]) {
                requestBuilder.append("Body: ").append(new String((byte[]) requestBody)).append("\n");
            } else {
                // For other types, try toString() or consider converting to JSON
                requestBuilder.append("Body: ").append(requestBody).append("\n");
            }
        }

        return requestBuilder.toString();
    }


    private String buildResponseDetails(Response response) {
        String responseBuilder = "Status Code: " + response.getStatusCode() + "\n" +
                "Headers: " + response.getHeaders() + "\n" +
                "Body: \n" + response.getBody().prettyPrint();
        return responseBuilder;
    }

}
