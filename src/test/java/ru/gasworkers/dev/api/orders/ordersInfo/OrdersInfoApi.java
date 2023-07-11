package ru.gasworkers.dev.api.orders.ordersInfo;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.ordersInfo.dto.OrdersInfoRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersInfoApi extends BaseApi {
    //    post https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-info
    @Step("API: Получение информации о заказе")
    public ValidatableResponse infoOrder(String token, Integer orderId) {
        OrdersInfoRequestDto ordersInfoRequestDto = new OrdersInfoRequestDto();
        ordersInfoRequestDto.setOrder_id(orderId);

        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(ordersInfoRequestDto)
                .post("/orders/info")
                .then().spec(baseResponseSpec);
    }
}
