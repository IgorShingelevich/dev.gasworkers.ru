/**
 * The AuthorizationApi class provides methods for user authorization
 * and obtaining a token for further API usage.
 */
package ru.gasworkers.dev.api.authorisation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthorizationApi {

    /**
     * Performs user authorization using email, phone, or login and password.
     * Returns the authorization token.
     *
     * @param userType   The type of the application (client/master). Required for mobile.
     * @param email      The user's email. Required if phone and login are not provided.
     * @param phone      The user's phone number. Required if email and login are not provided.
     * @param login      The user's login. Required if email and phone are not provided.
     * @param password   The user's password. Required.
     */
    public void authorization(String userType, String email, String phone, String login, String password) {
        String requestBody = "{"
                + "\"email\": \"" + email + "\","
                + "\"phone\": \"" + phone + "\","
                + "\"login\": \"" + login + "\","
                + "\"password\": \"" + password + "\","
                + "\"type\": \"" + userType + "\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://api.dev.gasworkers.ru/api/v1/auth/login")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", equalTo(0))
                .body("message", equalTo("Успешно авторизован"))
                .body("data.token", notNullValue())
                .extract()
                .response();

        String token = response.path("data.token");
        System.out.println("Token: " + token);
    }
}
