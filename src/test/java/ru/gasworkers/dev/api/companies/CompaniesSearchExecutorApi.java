package ru.gasworkers.dev.api.companies;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;

import static io.restassured.RestAssured.given;

public class CompaniesSearchExecutorApi extends BaseApi {
//    https://dev.gasworkers.ru/api/companies/search-executor

    @Step("API: companies/search-executor")
    public ValidatableResponse searchExecutor(CompaniesSearchExecutorRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(inputDto)
                .post("/companies/search-executor")
                .then().spec(baseResponseSpec);
    }
}
