package ru.gasworkers.dev.api.registration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import ru.gasworkers.dev.model.apiModel.UserType;

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
        Allure.step("Окночание - Регистрация Api для роли: " + userType + " " + email + " " + phone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + userType + "\","
                    + "\"password\": \"" + password + "\","
                    + "\"email\": \"" + email + "\","
                    + "\"phone\": " + phone + ","
                    + "\"first_name\": \"" + name + "\","
                    + "\"last_name\": \"" + surname + "\","
                    + "\"middle_name\": \"" + patronymic + "\","
                    + "\"gender\": \"" + gender + "\","
                    + "\"is_have_contract\": " + isHaveContract + ","
                    + "\"is_ip\": " + isIp + ","
                    + "\"employed_status\": \"" + employedStatus + "\","
                    + "\"service_id\": " + serviceId
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

            System.out.println("Register finish for role: " + userType+ " " + email + " " + phone + " " + name + " " + surname + " " + patronymic);

        });
    }
}
