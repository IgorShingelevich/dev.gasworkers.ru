package ru.gasworkers.dev.api.registration.regularRegistration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;

import static io.restassured.RestAssured.given;

public class StartRegularRegistrationApi extends BaseApi {

    @Step("API: Регулярная регистрация Начало")
    public ValidatableResponse startRegistration(StartRegistrationInputDto inputDto) {
        return given().spec(baseRequestSpec)
                // do not understand how object is converted to json
                .body(inputDto)
                .when()
                .post("/auth/register")
                .then().spec(baseResponseSpec);
    }

}
