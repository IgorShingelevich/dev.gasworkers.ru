package ru.gasworkers.dev.api.orders.id;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class OrdersIdApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-GETapi-v1-orders--id-
    @Step("API: Получение информации о заказе по ID")
    public ValidatableResponse orderId(Integer orderId, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/orders/" + orderId)
                .then().spec(baseResponseSpec);
    }
}
