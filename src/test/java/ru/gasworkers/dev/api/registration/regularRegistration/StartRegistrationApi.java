package ru.gasworkers.dev.api.registration.regularRegistration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationRequestDto;

import static io.restassured.RestAssured.given;

public class StartRegistrationApi extends BaseApi {

    @Step("API: Регулярная регистрация Начало")
    public ValidatableResponse startRegistration(StartRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                // do not understand how object is converted to json
                .body(inputDto)
                .when()
                .post("/auth/register")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Регулярная регистрация Начало")
    public ValidatableResponse startRegistration(String type, String email, String phone, Boolean isPhoneSend) {
        StartRegistrationRequestDto inputDto = StartRegistrationRequestDto.builder()
                .type(type)
                .email(email)
                .phone(phone)
                .isPhoneSend(isPhoneSend)
                .build();
         return startRegistration(inputDto);

    }

}
