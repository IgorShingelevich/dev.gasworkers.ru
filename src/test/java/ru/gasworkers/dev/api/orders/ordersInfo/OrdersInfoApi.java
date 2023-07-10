package ru.gasworkers.dev.api.orders.ordersInfo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class OrdersInfoApi extends BaseApi {
    //    post https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-info
    // get
    @Step("API: Получение информации о заказе")
    public ValidatableResponse getInfoOrder(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/orders/info")
                .then().spec(baseResponseSpec);
    }
}
