package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ThroughClientRegistrationApi {

    public void repairThroughRegistration(String clientEmail, String clientPhone, String clientDescription) {
        Allure.step("Фоновая регистрация Api: " + clientEmail + " " + clientPhone, () -> {

            String requestBody = "{"
                    + "\"type\": \"repair\","
                    + "\"object_id\": 10,"
                    + "\"address_id\": 7,"
                    + "\"start_date\": \"2023-04-14T12:05:23\","
                    + "\"end_date\": \"2023-04-15T12:05:23\","
                    + "\"time\": 19,"
                    + "\"time_started\": \"2023-04-14T12:05:23\","
                    + "\"time_ended\": \"2023-04-15T12:05:23\","
                    + "\"email\": \"" + clientEmail + "\","
                    + "\"phone\": " + clientPhone + ","
                    + "\"description\": \"" + clientDescription + "\""
                    + "}";

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/register/through")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Registration completed"));

            System.out.println("email: " + clientEmail);
            System.out.println("phone: " + clientPhone);
            System.out.println("description: " + clientDescription);

        });
    }


}
