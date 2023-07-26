package ru.gasworkers.dev.api.orders.createAct;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class OrdersCreateActApi extends BaseApi {
    // https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-create-act

    @Step("API: Create act")
    public ValidatableResponse createAct(OrdersCreateActRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/create-act")
                .then().spec(baseResponseSpec);
    }
}
