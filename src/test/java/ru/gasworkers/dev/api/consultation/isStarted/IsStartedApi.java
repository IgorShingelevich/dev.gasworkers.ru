package ru.gasworkers.dev.api.consultation.isStarted;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedRequestDto;

import static io.restassured.RestAssured.given;

public class IsStartedApi extends BaseApi {
    @Step("API: Is started")
    public ValidatableResponse isStarted(IsStartedRequestDto inputDto, String token) {
        return given().spec(baseRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body(inputDto)
                .when()
                .post("/consultation/is-started")
                .then().spec(baseResponseSpec);
    }
}
