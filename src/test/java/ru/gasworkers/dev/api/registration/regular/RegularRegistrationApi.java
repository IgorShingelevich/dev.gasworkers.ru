package ru.gasworkers.dev.api.registration.regular;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

import static io.restassured.RestAssured.given;

public class RegularRegistrationApi extends BaseApi {

    //--------------------------------------------START-----------------------------------------------------------------
    @Step("API: Регулярная регистрация [Начало]")
    public ValidatableResponse startRegistration(StartRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                // do not understand how object is converted to json
                .body(inputDto)
                .when()
                .post("/auth/register")
                .then().spec(baseResponseSpec);
    }

    public ValidatableResponse startRegistration(String type, String email, String phone, Boolean isPhoneSend) {
        StartRegistrationRequestDto inputDto = StartRegistrationRequestDto.newInstance(type, email, phone, isPhoneSend);
        return startRegistration(inputDto);
    }

    //----------------------------------------------CHECK---------------------------------------------------------------
    @Step("API: Регулярная регистрация [Проверка кода СМС]")
    public ValidatableResponse checkRegistration(CheckRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/check-register-code")
                .then().spec(baseResponseSpec);
    }

    public ValidatableResponse checkRegistration(Integer code, String type, String email, String phone) {
        CheckRegistrationRequestDto inputDto = CheckRegistrationRequestDto.newInstance(code, type, email, phone);
        return checkRegistration(inputDto);
    }

    //----------------------------------------------FINISH---------------------------------------------------------------
    @Step("API: Регулярная регистрация [Завершение]")
    public ValidatableResponse finishRegistration(FinishRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/complete-registration")
                .then().spec(baseResponseSpec);
    }

}
