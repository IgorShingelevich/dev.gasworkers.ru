package ru.gasworkers.dev.api.orders.info;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersInfoApi extends BaseApi {
    //    post https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-info

    @Step("API: Получение информации о заказе")
    public ValidatableResponse ordersInfo(OrdersInfoRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .post("/orders/info")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Получение информации о заказе")
    public ValidatableResponse ordersInfo(Integer orderId, String token) {
        return ordersInfo(OrdersInfoRequestDto.builder()
                .orderId(orderId)
                .build(), token);
    }
}
