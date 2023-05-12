package ru.gasworkers.dev.api;

import io.restassured.RestAssured;

public class ApiTestConfig {
    public static final String BASE_URL = "https://api.dev.gasworkers.ru/api/v1";

    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URL;
    }
    
}
