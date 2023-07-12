package ru.gasworkers.dev.api.users.fspBankList;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class FspBankListApi extends BaseApi {

    // https://api.dev.gasworkers.ru/docs#endpoints-POSTapi-v1-users-fps-bank-list
    @Step("API: Get FSP bank list")
    public ValidatableResponse fspBankList(String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/users/fps-bank-list")
                .then().spec(baseResponseSpec);
    }
}
