package ru.gasworkers.dev.api.orders.actions;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersSaveActionsApi extends BaseApi {
    // https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-save-actions
    @Step("API: Save actions")
    public ValidatableResponse saveActions(OrdersSaveActionsRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/save-actions")
                .then().spec(baseResponseSpec);
    }
}
