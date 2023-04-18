package ru.gasworkers.dev.api.registration.throughClientRegistrationApi;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MaintenanceThroughClientRegistrationApi {

    /**
     * Performs the through registration process for the specified client
     * using the given parameters.
     *
     * @param addressId  The ID of the address where the service will be performed.
     * @param equipments The equipment objects for the service.
     *                    Example: "[{\"type_id\":1,\"brand_id\":11,\"custom_brand\":null,\"model_id\":563,\"custom_model\":null,\"power\":\"25.8\"}]"
     * @param email      The email address of the client. Must be a valid email address.
     *                    Example: "example@gmail.com"
     * @param phone      The phone number of the client. Must start with one of 7, and be 11 digits long.
     *                    Example: "70016535345"
     * @param orderType  The type of service requested. Must be one of "repair", "maintenance", or "consultation".
     * @param startDate  The start date of the service. Must be a valid date string in ISO 8601 format.
     *                    Example: "2023-04-18T09:00:00.000Z"
     * @param endDate    The end date of the service. Must be a valid date string in ISO 8601 format.
     *                    Example: "2023-04-20T09:00:00.000Z"
     * @param time       The time of the service. Must be an integer value.
     *                    Example: 1
     */
    public void throughRegistration(int addressId, String equipments, String email, String phone, String orderType,
                                    String startDate, String endDate, int time) {

        String requestBody =
                "{\n" +
                        "    \"address_id\": " + addressId + ",\n" +
                        "    \"equipments\": " + equipments + ",\n" +
                        "    \"email\": \"" + email + "\",\n" +
                        "    \"phone\": \"" + phone + "\",\n" +
                        "    \"type\": \"" + orderType + "\",\n" +
                        "    \"start_date\": \"" + startDate + "\",\n" +
                        "    \"end_date\": \"" + endDate + "\",\n" +
                        "    \"time\": " + time + "\n" +
                        "}";

        Allure.step("Through client registration API: " + email + " " + phone, () -> {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("https://dev.gasworkers.ru/api/auth/register/through")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("auth__register_through_code_sent")) //Успешная регистрация
                    .extract().response();

            // sout response
            System.out.println("Started Through client registration API: " + email + " " + phone);
            System.out.println("Response: " + response.asString());
        });
    }

}
