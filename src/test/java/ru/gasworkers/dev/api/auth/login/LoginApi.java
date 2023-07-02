package ru.gasworkers.dev.api.auth.login;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.extension.user.User;

import static io.restassured.RestAssured.given;

public class LoginApi extends BaseApi {

    @Step("API: Регулярная регистрация [Логин]")
    public ValidatableResponse login(LoginRequestDto inputDto) {
        return given().spec(baseRequestSpec)
                .body(inputDto)
                .when()
                .post("/auth/login")
                .then().spec(baseResponseSpec);
    }

    public String getToken(User user) {
        LoginRequestDto inputDto = LoginRequestDto.asUser(user.getPhone(), user.getPassword());
        return login(inputDto)
                .statusCode(200)
                .extract().as(LoginResponseDto.class)
                .getData().getToken();
    }
}
