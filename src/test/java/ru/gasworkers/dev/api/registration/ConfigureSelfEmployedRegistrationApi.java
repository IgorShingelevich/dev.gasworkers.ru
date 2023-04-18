package ru.gasworkers.dev.api.registration;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ConfigureSelfEmployedRegistrationApi {

    /**
     * Completes the registration process for a self-employed user.
     *
     * @param isHaveContract   whether the user has a contract
     * @param isIp             whether the user is an individual entrepreneur
     */
    public void configureSelfEmployedRegistration(boolean isHaveContract, boolean isIp) {
        String requestBody = "{"
                + "\"is_have_contract\": " + isHaveContract + ","
                + "\"is_ip\": " + isIp
                + "}";

        ExtractableResponse<Response> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://api.dev.gasworkers.ru/api/v1/self-employed/complete")
                .then()
                .statusCode(anyOf(is(200), is(201), is(202), is(204)))
                .contentType(ContentType.JSON)
                .body("status", equalTo(0))
                .extract();

        if (response.statusCode() != 200) {
            System.out.println("Finish register Self-employed  error Request : " + requestBody);
            System.out.println("Finish register Self-employed  error Response : " + response.asString());
        } else {
            System.out.println("Finish register Self-employed success Request : " + requestBody);
            System.out.println("Finish register Self-employed success Response : " + response.asString());
        }
        System.out.println("Finish register  for self-employed " + isHaveContract + " " + isIp);
    }
}
