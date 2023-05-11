package ru.gasworkers.dev.api.administration;

import io.qameta.allure.Allure;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class usersDeleteApi {

    /**
     * Массовое удаление пользователей администратором системы.
     *
     * @param type     Тип параметра удаления пользователя (phone, email, middle_name). Example: "phone".
     * @param dateFrom Nullable Дата начала выборки as Unix timestamp. Example: 1682662864.
     * @param dateTo   Nullable Тип конца выборки as Unix timestamp. Example: 1682662864.
     */

    public Response deleteUsersFromToRange(String type, Integer dateFrom, Integer dateTo) {
        Allure.step("Массовое удаление пользователей администратором системы с-по дату", () -> {
        });

        String requestBody = "{" +
                "\"type\":\"" + type + "\"," +
                "\"date_from\":" + dateFrom + "," +
                "\"date_to\":" + dateTo +
                "}";

        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody);

        Response response = request
                .when()
                .post("/api/v1/admin/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print request details
        System.out.println("Request body:");
        System.out.println(requestBody);
        Allure.addAttachment("Request Details", request.toString());

        // Print response details
        System.out.println("Response:");
        System.out.println(response.prettyPrint());

        System.out.println("Deleted users by type: " + type + " and date range: " + dateFrom + " - " + dateTo);
        System.out.println();

        return response;
    }

    public Response deleteUsersFrom(String type, Integer dateFrom) {
        Allure.step("Массовое удаление пользователей администратором системы с даты", () -> {
        });

        String requestBody = "{" +
                "\"type\":\"" + type + "\"," +
                "\"date_from\":" + dateFrom + "," +
                "}";

        RequestSpecification request = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("accept-language", "en-US,en;q=0.9,ru;q=0.8")
                .body(requestBody);

        Response response = request
                .when()
                .post("/api/v1/admin/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Print request details
        System.out.println("Request body:");
        System.out.println(requestBody);
        Allure.addAttachment("Request Details", request.toString());

        // Print response details
        System.out.println("Response:");
        System.out.println(response.prettyPrint());

        System.out.println("Deleted users by type: " + type + " and date range: " + dateFrom);
        System.out.println();

        return response;
    }


}
