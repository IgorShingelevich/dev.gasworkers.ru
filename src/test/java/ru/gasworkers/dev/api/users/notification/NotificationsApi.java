package ru.gasworkers.dev.api.users.notification;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class NotificationsApi extends BaseApi {
    //     https://api.dev.gasworkers.ru/docs#uvedomleniia-GETapi-v1-notifications
    @Step("API: Get notifications")
    public ValidatableResponse getNotifications(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/notifications")
                .then().spec(baseResponseSpec);
    }

    //    https://api.dev.gasworkers.ru/docs#uvedomleniia-POSTapi-v1-notifications-read-all
    @Step("API: Read all notifications")
    public ValidatableResponse readAllNotifications(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/notifications/read-all")
                .then().spec(baseResponseSpec);
    }
}
