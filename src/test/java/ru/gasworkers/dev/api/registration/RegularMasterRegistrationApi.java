package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RegularMasterRegistrationApi {


    public void regularMasterRegistration(String masterEmail, String masterPhone, String masterName, String masterSurname, String masterPatronymic, String masterPassword) {
        Allure.step("Регистрация мастера Api: " + masterEmail + " " + masterPhone, () -> {

            String requestBody = "{"
                    + "\"type\": \"master\","
                    + "\"password\": \"" + masterPassword + "\","
                    + "\"email\": \"" + masterEmail + "\","
                    + "\"phone\": " + masterPhone + ","
                    + "\"first_name\": \"" + masterName + "\","
                    + "\"last_name\": \"" + masterSurname + "\","
                    + "\"middle_name\": \"" + masterPatronymic + "\","
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

            System.out.println("email: " + masterEmail);
            System.out.println("phone: " + masterPhone);
            System.out.println("fullName: " + masterName + " " + masterSurname + " " + masterPatronymic);

        });
    }

}
