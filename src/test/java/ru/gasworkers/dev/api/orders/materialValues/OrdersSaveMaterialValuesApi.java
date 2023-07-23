package ru.gasworkers.dev.api.orders.materialValues;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesRequestDto;

import static io.restassured.RestAssured.given;

public class OrdersSaveMaterialValuesApi extends BaseApi {
//https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-save-material-values

    @Step("API: Сохранение таблицы материалов к ремонту")
    public ValidatableResponse saveMaterialValues(OrdersSaveMaterialValuesRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when().post("/orders/save-material-values")
                .then().spec(baseResponseSpec);
    }
}
