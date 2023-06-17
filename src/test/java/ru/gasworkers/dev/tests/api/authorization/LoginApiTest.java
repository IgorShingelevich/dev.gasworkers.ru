package ru.gasworkers.dev.tests.api.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.authorisation.LoginApi;
import ru.gasworkers.dev.api.authorisation.LoginRequestDTO;
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

public class LoginApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    private final LoginApi loginApi = new LoginApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(LoginPositiveCase.class)
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.AUTHORIZATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.AUTHORISATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Success case:")
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

        step("Authorization", () ->
            loginApi.authorization(testCase.getLoginDto())
                    .statusCode(200));
    }
}
