package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.net.URLDecoder;

import static io.restassured.RestAssured.given;

public class RegularStartRegistrationApi1 {

    public Response regularStartRegistration(String userType, String email, String phone, Boolean isPhoneSend) {
        // InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email + " " + phone, () -> {
        });
        //request header


        String requestBody = "{" +
                "\"type\":" + userType + "," +
                "\"email\":" + email + "," +
                "\"phone\":\"" + phone + "\"," +
                "\"phone_send\":" + isPhoneSend +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200)

                .extract()
                .response();
        System.out.println(response.toString());



      /*  if (response.statusCode() != 200) {
            System.out.println("Start register != 200 Request: " + requestBody);
            System.out.println("Start register != 200 Response: " + response.asString());
        }
        if (response.statusCode() == 200) {
            System.out.println("Start register successes  Request: " + requestBody);

            String responseString = response.asString();

        }*/

        System.out.println("Start register for role: " + userType + " " + email + " " + phone);
        System.out.println();

        return response;
    }

    public Response regularStartRegistration(String userType, String email, Boolean isPhoneSend) {
        // InputValidatorApi.validateRegularStartRegistration(userType, email, phone, isPhoneSend);

        Allure.step("Начало - Регистрация  Api: " + email, () -> {
        });
        //request header


        String requestBody = "{" +
                "\"type\":\"" + userType + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"phone\":\"\"," +
                "\"phone_send\":" + isPhoneSend +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody)
                .response()
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.toString());



       /* if (response.statusCode() != 200) {
            System.out.println("Start register != 200 Request: " + requestBody);
            System.out.println("Start register != 200 Response: " + response.asString());
        }
        if (response.statusCode() == 200) {
            System.out.println("Start register successes  Request: " + requestBody);

            String responseString = response.asString();

        }*/

        System.out.println("Start register for role: " + userType + " " + email);
        System.out.println();

        return response;
    }

}
