package ru.gasworkers.dev.api.orders.approveDate;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class OrdersApproveDateApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-approve-date
    @Step("API: Установка даты проведения заказа")
    public ValidatableResponse ordersApproveDate(OrdersApproveDateRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/approve-date")
                .then().spec(baseResponseSpec);
    }
}
