package ru.gasworkers.dev.tests.apiTest;

import io.restassured.RestAssured;

public class configApiTest {

    public static final String BASE_URL = "https://api.dev.gasworkers.ru/api/v1";

    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URL;
    }
}
