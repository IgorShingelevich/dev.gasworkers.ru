package ru.gasworkers.dev.api.registration.regularRegistration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;

import static io.restassured.RestAssured.given;

public class StartRegularRegistrationApi extends BaseApi {

    @Step("API: Начало регистрации")
    public ValidatableResponse startRegistration(StartRegistrationInputDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/register")
                .then().spec(baseResponseSpec);
    }

}
