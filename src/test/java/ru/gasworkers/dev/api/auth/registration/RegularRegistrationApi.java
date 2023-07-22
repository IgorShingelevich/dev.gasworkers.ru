package ru.gasworkers.dev.api.auth.registration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.check.CheckThroughRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationRequestDto;

import static io.restassured.RestAssured.given;

public class RegularRegistrationApi extends BaseApi {


    //-------------------------------------COMPLEX REGISTRATION---------------------------------------------------------
    @Step("API: Комплексная регулярная регистрация")
    public void complexRegistration(ComplexRegistrationRequestDto complexDto) {
        startRegistration(complexDto.toStartRegistration()).statusCode(200);
        checkRegistration(complexDto.toCheckRegularRegistration()).statusCode(200);
        finishRegistration(complexDto.toFinishRegistration()).statusCode(200);
    }

    //-------------------------------------COMPLEX THROUGH REGISTRATION-------------------------------------------------
    @Step("API: Комплексная фоновая регистрация")
    public void complexThroughRegistration(ComplexRegistrationRequestDto complexDto) {
        startThroughRegistration(complexDto.toStartThroughRegistration()).statusCode(200);
        checkThroughRegistration(complexDto.toCheckThroughRegistration()).statusCode(200);
    }

    //--------------------------------------------START-----------------------------------------------------------------
    @Step("API: Регулярная регистрация [Начало]")
    public ValidatableResponse startRegistration(StartRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
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
                .header("abracadabra", "randomValue")
                .body(inputDto)
                .when()
                .post("/auth/check-register-code")
                .then().spec(baseResponseSpec);
    }

    public ValidatableResponse checkRegistration(Integer code, String type, String email, String phone) {
        CheckRegistrationRequestDto inputDto = CheckRegistrationRequestDto.newInstanceRegularRegistration(code, type, email, phone);
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

    //    -------------------------START THROUGH REGISTRATION-----------------------------------------------------------
    @Step("API: Фоновая регистрация [Начало]")
    public ValidatableResponse startThroughRegistration(StartThroughRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/register/through")
                .then().spec(baseResponseSpec);
    }

    //    -------------------------CHECK THROUGH REGISTRATION----------------------------------------------------------
    @Step("API: Фоновая регистрация [Проверка кода СМС]")
    public ValidatableResponse checkThroughRegistration(CheckThroughRegistrationRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/register/through/sign")
                .then().spec(baseResponseSpec);

    }

}
