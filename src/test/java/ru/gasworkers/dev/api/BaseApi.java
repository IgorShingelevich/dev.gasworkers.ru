package ru.gasworkers.dev.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public abstract class BaseApi {

    protected final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.dev.gasworkers.ru/api/v1")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("accept-language", "en-US,en;q=0.9,ru;q=0.8")
//            .log(LogDetail.BODY)
            .build()
            .log().ifValidationFails()
            .log().body();

    protected final ResponseSpecification baseResponseSpec = new ResponseSpecBuilder().log(ALL).build();

}
