package ru.gasworkers.dev.api.orders.transfer;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class OrdersTransferApi extends BaseApi {
//    https://dev.gasworkers.ru/api/orders/transfer

    @Step("Api: orders/transfer")
    public ValidatableResponse transfer(OrderTransferRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/transfer")
                .then().spec(baseResponseSpec);
    }
}
