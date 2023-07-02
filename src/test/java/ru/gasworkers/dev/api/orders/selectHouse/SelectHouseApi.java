package ru.gasworkers.dev.api.orders.selectHouse;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseRequestDto;

import static io.restassured.RestAssured.given;

public class SelectHouseApi extends BaseApi {
    //    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-object
    @Step("API: Select object")
    public ValidatableResponse selectObject(SelectHouseRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/select-object")
                .then().spec(baseResponseSpec);
    }
}
