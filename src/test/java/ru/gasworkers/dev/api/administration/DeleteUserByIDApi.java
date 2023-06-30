package ru.gasworkers.dev.api.administration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserByIDApi {

    public Response deleteUserById(String userId) {
        Allure.step("Удаление пользователя администратором системы по ИД: " + userId, () -> {
        });

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .when()
                .post("/api/v1/admin/users/" + userId)
                .then()
//                .statusCode(200)
                .extract()
                .response();
        System.out.println("Response " + response.toString());

        System.out.println("User with ID: " + userId + " has been deleted.");
        System.out.println();

        return response;
    }



}
