package ru.gasworkers.dev.api.orders.selectMaster;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class SelectMasterApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-master
    @Step("API: Select master")
    public ValidatableResponse selectMaster(SelectMasterRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/select-master")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Select master")
    public ValidatableResponse selectMaster(Integer orderId, Integer masterId, String token) {
        return selectMaster(SelectMasterRequestDto.builder()
                .orderId(orderId)
                .masterId(masterId)
                .build(), token);
    }
}
