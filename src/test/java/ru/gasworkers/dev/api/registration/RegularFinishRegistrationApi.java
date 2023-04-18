package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * This class provides the methods for completing the user registration process.
 */
public class RegularFinishRegistrationApi {

    /**
     * Completes the registration process for a client or master.
     * After a successful call to this method, a fully registered user will be created.
     *
     * @param userType             the type of registering user (client/master/service)
     * @param email      the email address of the registering user (mandatory if registration is via phone)
     * @param phone      the phone number of the registering user (mandatory if registration is via email)
     * @param name       the first name of the registering user
     * @param surname    the last name of the registering user
     * @param patronymic the middle name of the registering user (optional)
     * @param password   the password of the registering user
     * @param gender           the gender of the registering user (male/female)
     * @param isHaveContract   whether the user has a contract (optional)
     * @param isIp             whether the user is an individual entrepreneur (optional)
     * @param employedStatus   the employment status of the user (pending/accepted/self-employed)
     * @param serviceId        the identifier of the company the user works for (mandatory for master registration)
     */
    public void regularFinishRegistration(String userType, String email, String phone, String name, String surname, String patronymic, String password, String gender, Boolean isHaveContract, Boolean isIp, String employedStatus, Integer serviceId) {
        Allure.step("Окончание - Регистрация Api для роли: " + userType + " " + email + " " + phone, () -> {

            String requestBody = "{"
                    + "\"email\": \"" + email + "\","
                    + "\"employed_status\": \"" + employedStatus + "\","
                    + "\"first_name\": \"" + name + "\","
                    + "\"gender\": \"" + gender + "\","
                    + "\"last_name\": \"" + surname + "\","
                    + "\"middle_name\": \"" + patronymic + "\","
                    + "\"password\": \"" + password + "\","
                    + "\"phone\": " + phone + ","
                    + "\"service_id\": " + serviceId + ","
                    + "\"is_have_contract\": " + isHaveContract + ","
                    + "\"is_ip\": " + isIp + ","
                    + "\"type\": \"" + userType + "\""
                    + "}";

            ExtractableResponse<Response> response =  given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/complete-registration")
                    .then()
                    .statusCode(anyOf(is(200), is(201), is(202), is(204))) // add acceptable codes for your API
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Регистрация завершена"))
                    .extract();
            if (response.statusCode() != 200) {
                System.out.println("Register finish error Request : " + requestBody);
                System.out.println("Register finish error Response : " + response.asString());
            }
            if (response.statusCode() == 200) {
                System.out.println("Register finish success Request : " + requestBody);
                System.out.println("Register finish success Response : " + response.asString());
            }

            System.out.println("Register finish for role: " + userType+ " " + email + " " + phone + " " + name + " " + surname + " " + patronymic);
            System.out.println();
        });
    }
}
