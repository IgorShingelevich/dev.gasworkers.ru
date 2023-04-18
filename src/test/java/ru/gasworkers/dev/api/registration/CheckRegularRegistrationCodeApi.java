package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import ru.gasworkers.dev.api.helperApi.InputValidatorApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CheckRegularRegistrationCodeApi {

    /**
     * This method checks the registration code and returns a success response if the code is valid.
     *
     * @param code     Integer code sent to user's phone or email. Example: 123456
     * @param userType UserType enum representing type of user being registered (client/master/service). Example: UserType.CLIENT
     * @param email    String email of user being registered (mandatory if phone is not provided). Optional. Example: client@gmail.com
     * @param phone    String phone of user being registered (mandatory if email is not provided). Optional. Example: "79119129233"
     */
    public void checkRegularRegistrationCode(String code, String userType, String email, String phone) {
//        InputValidatorApi.validateRegistrationCode(code, userType, email, phone);

        Allure.step("Проверка регистрационного кода: " + code + " для роли: " + userType + " " + email + " " + phone, () -> {
            String requestBody = "{"
                    + "\"code\": " + code + ","
                    + "\"type\": \"" + userType + "\","
                    + "\"email\": \"" + email + "\","
                    + "\"phone\": " + phone + "}";

            ExtractableResponse<Response> response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/check-register-code")
                    .then()
                    .extract();

            if (response.statusCode() != 200) {
                System.out.println("Checked  != 200 Request: " + requestBody);
                System.out.println("Checked  !=  Response: " + response.asString());
            }
            if (response.statusCode() == 200) {
                System.out.println("Checked register code Request : " + requestBody);
                System.out.println("Checked register code Response : " + response.asString());
            }

            response.response().then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Код успешно проверен"))
                    .body("data", hasSize(0));

            System.out.println("Checked register code: " + code + " for: " + userType + " " + email + " " + phone);
            System.out.println();
        });
    }
}