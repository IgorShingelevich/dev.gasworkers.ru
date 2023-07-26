package ru.gasworkers.dev.api.orders.sign;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSendSignRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersSendSignApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-send-sign
    @Step("API: Send sign")
    public ValidatableResponse sendSign(OrdersSendSignRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/send-sign")
                .then().spec(baseResponseSpec);
    }
}
