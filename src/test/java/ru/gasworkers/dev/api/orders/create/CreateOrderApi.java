package ru.gasworkers.dev.api.orders.create;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;

import static io.restassured.RestAssured.given;

public class CreateOrderApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-create
    @Step("API: Create order")
    public ValidatableResponse createOrder(CreateOrderRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/create")
                .then().spec(baseResponseSpec);
    }
}
