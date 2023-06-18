package ru.gasworkers.dev.api.registration.regular;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.authorisation.LoginRequestDTO;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

import static io.restassured.RestAssured.given;


public class RegularRegistrationApi extends BaseApi {
    @Getter
    @Setter
    private static String loginToken;

    //-------------------------------------COMPLEX REGISTRATION---------------------------------------------------------
    @Step("API: Комплексная регулярная регистрация")
    public void complexRegistration(ComplexRegistrationRequestDto complexDto) {
        startRegistration(complexDto.toStartRegistration()).statusCode(200);
        checkRegistration(complexDto.toCheckRegistration()).statusCode(200);
        finishRegistration(complexDto.toFinishRegistration()).statusCode(200);
        login(complexDto.toLogin()).statusCode(200);
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

    //----------------------------------------------LOGIN----------------------------------------------------------------
    @Step("API: Регулярная регистрация [Логин]")
    public ValidatableResponse login(LoginRequestDTO inputDto) {
        Response response = given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/login")
                .then().spec(baseResponseSpec)
                .extract().response();

        // Extract the token from the response body
        String token = response.jsonPath().getString("data.token");

        // Store the token for later use
        setLoginToken(token);

        return response.then().spec(baseResponseSpec);
    }
}
