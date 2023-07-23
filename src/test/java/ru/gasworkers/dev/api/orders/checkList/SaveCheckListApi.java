package ru.gasworkers.dev.api.orders.checkList;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class SaveCheckListApi extends BaseApi {
    //https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-save-check-list
    @Step("API: сохранение чек листа")
    public ValidatableResponse saveCheckList(SaveCheckListRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/orders/save-check-list")
                .then().spec(baseResponseSpec);
    }
}
