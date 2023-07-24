package ru.gasworkers.dev.api.orders.actions;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersActionsApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-actions
    @Step("API:  Создать Таблицу проведенных работ по заказу (ремонт)")
    public ValidatableResponse actionsTable(OrdersActionsRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/actions")
                .then()
                .spec(baseResponseSpec);
    }
}
