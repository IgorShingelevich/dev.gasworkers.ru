package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.gasworkers.dev.api.helperApi.InputValidatorApi;
import ru.gasworkers.dev.model.apiModel.UserType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegularStartRegistrationApi {

    /**
     * Registers a client, master, or service company.
     * This process does not register the user in the literal sense, but only creates a "registration attempt".
     *
     * @param userType  Type of user being registered (client/master/service) (required).
     * @param email     E-mail of the user being registered (optional, but mandatory if phone is not provided).
     * @param phone     Phone number of the user being registered (optional, but mandatory if email is not provided).
     * @param isPhoneSend Indicates whether the phone number should be sent or not (optional, default: false).
     */
    public void regularStartRegistration(UserType userType, String email, String phone, boolean isPhoneSend) {
        InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email + " " + phone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + userType + "\","
                    + "\"email\": \"" + email + "\","
                    + "\"phone\": \"" + phone + "\","
                    + "\"phone_send\": " + isPhoneSend
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
                    .body("message", equalTo("Успешная регистрация"))
                    .extract()
                    .response();
            System.out.println("Register start for role: " + userType + " " + email + " " + phone);
        });
    }
}
