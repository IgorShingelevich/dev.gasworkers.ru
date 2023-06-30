package ru.gasworkers.dev.tests.api.helperApiTest;

import io.restassured.RestAssured;
@Deprecated
public class ConfigApiTest {

    public static final String BASE_URL = "https://api.dev.gasworkers.ru/api/v1";

    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URL;
    }
}
