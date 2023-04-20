package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Optional;

import static io.restassured.RestAssured.given;

public class RegularStartRegistrationApi {

    public Response regularStartRegistration(String userType, Object email, Object phone, Object isPhoneSend) {
        Allure.step("Начало - Регистрация Api: " + email + " " + phone, () -> {});

        String requestBody = "{\"type\":\"" + userType + "\",\"email\":\"" + (email != null ? email.toString() : "") + "\",\"phone\":\"" + (phone != null ? phone.toString() : "") + "\",\"phone_send\":" + (isPhoneSend != null ? isPhoneSend.toString() : false) + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "ru-RU,ru;q=0.9")
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .extract()
                .response();

        if (response.statusCode() != 200) {
            System.out.println("Start register != 200 Request: " + requestBody);
            System.out.println("Start register != 200 Response: " + response.asString());
        }
        if (response.statusCode() == 200) {
            System.out.println("Start register Request: " + requestBody);

            String responseString = response.asString();
        }

        System.out.println("Start register for role: " + userType + " " + email + " " + phone);
        System.out.println();

        return response;
    }
}
