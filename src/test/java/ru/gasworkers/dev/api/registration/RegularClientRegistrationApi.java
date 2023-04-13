package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RegularClientRegistrationApi {

    public void regularClientRegistration(String clientEmail, String clientPhone, String clientName, String clientSurname, String clientPatronymic, String clientPassword) {
        Allure.step("Регистрация клиента Api: " + clientEmail + " " + clientPhone, () -> {

            String requestBody = "{"
                    + "\"type\": \"client\","
                    + "\"password\": \"" + clientPassword + "\","
                    + "\"email\": \"" + clientEmail + "\","
                    + "\"phone\": " + clientPhone + ","
                    + "\"first_name\": \"" + clientName + "\","
                    + "\"last_name\": \"" + clientSurname + "\","
                    + "\"middle_name\": \"" + clientPatronymic + "\","
                    + "\"gender\": \"male\","
                    + "\"is_have_contract\": true,"
                    + "\"is_ip\": true,"
                    + "\"employed_status\": \"odio\","
                    + "\"service_id\": 2"
                    + "}";

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/complete-registration")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Регистрация завершена"));

            System.out.println("email: " + clientEmail);
            System.out.println("phone: " + clientPhone);
            System.out.println("fullName: " + clientName + " " + clientSurname + " " + clientPatronymic);

        });
    }
}


