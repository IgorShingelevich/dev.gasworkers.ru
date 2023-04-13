package ru.gasworkers.dev.api.registration;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ShortClientRegistrationApi {

    public void shortClientRegistration(String email, String phone) {
        String requestBody = "{"
                + "\"type\": \"client\","
                + "\"email\": \"" + email + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"phone_send\": false"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", equalTo(0))
//                .body("message", equalTo("Register success"))
                .body("message", equalTo("Успешная регистрация"))
                .extract()
                .response();
    }


}
