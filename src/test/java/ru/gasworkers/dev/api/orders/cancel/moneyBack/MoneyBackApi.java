package ru.gasworkers.dev.api.orders.cancel.moneyBack;

import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class MoneyBackApi extends BaseApi {
//    https://dev.gasworkers.ru/api/orders/info?money-back-info=true

    public ValidatableResponse moneyBackInfo(MoneyBackRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .get("/orders/info?money-back-info=true")
                .then()
                .spec(baseResponseSpec);
    }
}
