package ru.gasworkers.dev.tests.api.auth.login;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.AUTHORIZATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.POSITIVE)
@Tag(AllureTag.API)
@Tag(AllureTag.LOGIN)

@DisplayName("Login API Test")
public class LoginApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LoginApi loginApi = new LoginApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(LoginPositiveCase.class)
    @DisplayName("Success case")
    void authorization(LoginPositiveCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () ->
                registrationApi.checkRegistration(testCase.getCheckDto())
                        .statusCode(200));

        step("Finish registration", () ->
                registrationApi.finishRegistration(testCase.getFinishDto())
                        .statusCode(200));

        step("Login", () ->
                loginApi.login(testCase.getLoginDto())
                        .statusCode(200));
    }
}
