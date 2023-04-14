package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.UserType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RegularFinishMasterRegistrationApi {


    public void regularFinishMasterRegistration(String masterEmail, String masterPhone, String masterName, String masterSurname, String masterPatronymic, String masterPassword) {
        Allure.step("Окночание - Регистрация мастера Api: " + masterEmail + " " + masterPhone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + UserType.MASTER + "\","
                    + "\"password\": \"" + masterPassword + "\","
                    + "\"email\": \"" + masterEmail + "\","
                    + "\"phone\": " + masterPhone + ","
                    + "\"first_name\": \"" + masterName + "\","
                    + "\"last_name\": \"" + masterSurname + "\","
                    + "\"middle_name\": \"" + masterPatronymic + "\","
                    + "\"gender\": \"male\","
                    + "\"is_have_contract\": true,"
                    + "\"is_ip\": true,"
                    + "\"employed_status\": \"" + Employed_status.ACCEPTED + "\","
                    + "\"service_id\": 39"
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

            System.out.println("Register finish for master: " + masterEmail + " " + masterPhone + " " + masterName + " " + masterSurname + " " + masterPatronymic);
        });
    }

}
