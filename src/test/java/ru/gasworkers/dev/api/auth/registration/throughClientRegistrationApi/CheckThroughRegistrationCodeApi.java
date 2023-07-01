package ru.gasworkers.dev.api.auth.registration.throughClientRegistrationApi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class CheckThroughRegistrationCodeApi {

    /**
     * Checks the registration code and returns the API response.
     *
     * @param code     Integer code sent to user's phone or email. Example: 123456
     * @param email    String email of user being registered (mandatory if phone is not provided). Optional. Example: client@gmail.com
     * @param phone    String phone of user being registered (mandatory if email is not provided). Optional. Example: "79119129233"
     * @return the API response object
     * @throws IllegalArgumentException if any input parameter is invalid
     */
    public Response checkCode(int code, String email, String phone) {
        validateInput(code, email, phone);

        String requestBody = "{"
                + "\"code\": " + code + ","
                + "\"email\": \"" + email + "\","
                + "\"phone\": " + phone + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth/register/through/sign")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", equalTo(0))
                .body("message", equalTo("auth__register_through_code_checked"))
//                .body("data", hasSize(0))
                .extract()
                .response();

        System.out.println("Code: " + code + " checked for email: " + email + " and phone: " + phone);
        System.out.println(response.asString());

        return response;
    }

    /**
     * Validates the input parameters for the registration code API.
     *
     * @param code  Integer code sent to user's phone or email.
     * @param email String email of user being registered (mandatory if phone is not provided).
     * @param phone String phone of user being registered (mandatory if email is not provided).
     * @throws IllegalArgumentException if any input parameter is invalid
     */
    private void validateInput(int code, String email, String phone) {
        if (code <= 0) {
            throw new IllegalArgumentException("Invalid code: " + code);
        }

        if (email == null && phone == null) {
            throw new IllegalArgumentException("Either email or phone must be provided");
        }
    }
}
