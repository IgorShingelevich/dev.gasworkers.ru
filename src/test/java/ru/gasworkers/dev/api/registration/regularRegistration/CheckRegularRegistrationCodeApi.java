package ru.gasworkers.dev.api.registration.regularRegistration;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.gasworkers.dev.api.BaseApi;
import ru.gasworkers.dev.api.registration.dto.registration.CheckRegularRegistrationCodeInputDto;

import static io.restassured.RestAssured.given;

public class CheckRegularRegistrationCodeApi extends BaseApi {

    /*
     * This method checks the registration code and returns a success response if the code is valid.
     *
     * @param code     Integer code sent to user's phone or email. Example: 123456
     * @param userType UserType enum representing type of user being registered (client/master/service). Example: UserType.CLIENT
     * @param email    String email of user being registered (mandatory if phone is not provided). Optional. Example: client@gmail.com
     * @param phone    String phone of user being registered (mandatory if email is not provided). Optional. Example: "79119129233"
     */
    @Step("API: Регулярная регистрация Проверка  кода СМС")

    public ValidatableResponse checkRegularRegistrationCode(CheckRegularRegistrationCodeInputDto checkRegularRegistrationCodeInputDTO) {

        return given().spec(baseRequestSpec)
                .body(checkRegularRegistrationCodeInputDTO)
                .when()
                .post("/auth/check-register-code")
                .then().spec(baseResponseSpec);
    }

    @Step("API: Регулярная регистрация Проверка  кода СМС")

    public ValidatableResponse checkRegularRegistrationCode(Integer code, String type, String email, String phone) {
        CheckRegularRegistrationCodeInputDto checkRegularRegistrationCodeInputDTO = CheckRegularRegistrationCodeInputDto.builder()
                .code(code)
                .type(type)
                .email(email)
                .phone(phone)
                .build();
        return checkRegularRegistrationCode(checkRegularRegistrationCodeInputDTO);

    }

}

