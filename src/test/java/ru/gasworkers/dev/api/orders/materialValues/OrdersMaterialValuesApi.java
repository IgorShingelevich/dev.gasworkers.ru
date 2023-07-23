package ru.gasworkers.dev.api.orders.materialValues;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersMaterialValuesApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-material-values
    @Step("API: Создание таблицы материалов к ремонту")
    public ValidatableResponse createMaterialValues(OrdersMaterialValuesRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when().post("/orders/material-values")
                .then().spec(baseResponseSpec);
    }
}
