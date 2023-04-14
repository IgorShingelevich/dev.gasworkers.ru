package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.UserType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class RegularFinishSelfEmployedRegistrationApi {


    public void regularFinishSelfEmployedRegistration(String selfEmployedEmail, String selfEmployedPhone, String selfEmployedName, String selfEmployedSurname, String selfEmployedPatronymic, String selfEmployedPassword) {
        Allure.step("Окночание - Регистрация самозанятого  Api: " + selfEmployedEmail + " " + selfEmployedPhone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + UserType.MASTER + "\","
                    + "\"password\": \"" + selfEmployedPassword + "\","
                    + "\"email\": \"" + selfEmployedEmail + "\","
                    + "\"phone\": " + selfEmployedPhone + ","
                    + "\"first_name\": \"" + selfEmployedName + "\","
                    + "\"last_name\": \"" + selfEmployedSurname + "\","
                    + "\"middle_name\": \"" + selfEmployedPatronymic + "\","
                    + "\"gender\": \"" + Gender.MALE + "\","
                    + "\"is_have_contract\": true,"
                    + "\"is_ip\": true,"
                    + "\"employed_status\": \"" + Employed_status.SELF_EMPLOYED + "\","
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

            System.out.println("email: " + selfEmployedEmail);
            System.out.println("phone: " + selfEmployedPhone);
            System.out.println("fullName: " + selfEmployedName + " " + selfEmployedSurname + " " + selfEmployedPatronymic);

        });
    }

}
