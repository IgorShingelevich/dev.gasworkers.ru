package ru.gasworkers.dev.api.registration.regularRegistration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.dto.CheckRegularRegistrationCodeInputDto;
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

    @Step("API: Регулярная регистрация Начало")
    public ValidatableResponse startRegistration(String type, String email, String phone, Boolean isPhoneSend) {
        StartRegistrationInputDto inputDto = StartRegistrationInputDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .isPhoneSend(isPhoneSend)
                .build();
         return startRegistration(inputDto);

    }

}
