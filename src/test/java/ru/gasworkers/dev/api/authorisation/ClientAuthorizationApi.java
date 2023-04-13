package ru.gasworkers.dev.api.authorisation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ClientAuthorizationApi {


    public void testClientAuthorization() {
        String requestBody = "{"
                + "\"email\": \"shingelevich@gmail.com\","
                + "\"phone\": 79119129233,"
                + "\"login\": \"myLogin\","
                + "\"password\": \"123456\","
                + "\"type\": \"client\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("https://api.dev.gasworkers.ru/api/v1/auth/login")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", equalTo(0))
//                .body("message", equalTo("Auth success"))
                .body("message", equalTo("Успешно авторизован"))
                .body("data.token", notNullValue())
                .extract()
                .response();

        String token = response.path("data.token");
        System.out.println("Token: " + token);
    }


}
