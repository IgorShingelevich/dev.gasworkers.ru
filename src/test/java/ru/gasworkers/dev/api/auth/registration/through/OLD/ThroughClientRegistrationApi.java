package ru.gasworkers.dev.api.auth.registration.through.OLD;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ThroughClientRegistrationApi {

    /**
     * Performs the through registration process for the specified client
     * using the given parameters.
     *
     * @param orderType   Must be one of repair, maintenance, or consultation.
     * @param addressId   The address ID. Example: 7
     * @param equipments  The equipment object. Example: [{"type_id":1,"brand_id":11,"custom_brand":null,"model_id":563,"custom_model":null,"power":"25.8"}]
     * @param email       The client's email. Must be a valid email address. Example: donnelly.loyal@example.org
     * @param phone       The client's phone number. Must start with one of 7, and be 11 digits long. Example: 70015457645
     * @param description The client's description. Must not exceed 500 characters. Example: Eum voluptate maxime consequatur aut necessitatibus nostrum autem minima.
     * @param files       An array of file IDs. Example: [2875]
     * @param startDate   The start date. Must be a valid date string. Example: 2023-04-18T09:00:00.000Z
     * @param endDate     The end date. Must be a valid date string. Example: 2023-04-20T09:00:00.000Z
     * @param time        The time. Example: 1
     */
    public void throughRegistration(String orderType, Integer addressId, Object equipments, String email,
                                    Long phone, String description, String[] files, String startDate, String endDate,
                                    Integer time) {
        Allure.step("Фоновая регистрация Api: " + email + " " + phone, () -> {

            String requestBody = "{"
                    + "\"type\": \"" + orderType + "\","
                    + "\"address_id\": " + addressId + ","
                    + "\"equipments\": " + equipments + ","
                    + "\"start_date\": \"" + startDate + "\","
                    + "\"end_date\": \"" + endDate + "\","
                    + "\"time\": " + time + ","
                    + "\"email\": \"" + email + "\","
                    + "\"phone\": " + phone + ","
                    + "\"description\": \"" + description + "\","
                    + "\"files\": " + files
                    + "}";

            given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/auth/register/through")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("status", equalTo(0))
                    .body("message", equalTo("Registration completed"));

            System.out.println("email: " + email);
            System.out.println("phone: " + phone);
            System.out.println("description: " + description);

        });
    }
}
