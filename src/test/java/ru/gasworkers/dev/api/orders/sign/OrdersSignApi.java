package ru.gasworkers.dev.api.orders.sign;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSignRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersSignApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-sign
    @Step("API: Sign")
    public ValidatableResponse sign(OrdersSignRequestDto inpitDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inpitDto)
                .when()
                .post("/orders/sign")
                .then().spec(baseResponseSpec);
    }
}
