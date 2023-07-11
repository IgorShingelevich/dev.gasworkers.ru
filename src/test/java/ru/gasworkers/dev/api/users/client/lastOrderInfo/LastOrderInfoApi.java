package ru.gasworkers.dev.api.users.client.lastOrderInfo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class LastOrderInfoApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#zakazy-GETapi-v1-users-last-order-info
    @Step("API: Получение информации о последнем заказе")
    public ValidatableResponse getLastOrderInfo(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/last-order-info")
                .then().spec(baseResponseSpec);
    }
}
