package ru.gasworkers.dev.tests.api.auth.registration.regular.check;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.registration.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Story("Проверка регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckRegistrationApiTest extends BaseApiTest {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(CheckRegistrationPositiveCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case")
    void positiveTestCase(CheckRegistrationPositiveCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () -> {
            CheckRegistrationResponseDto expectedResponse = CheckRegistrationResponseDto.successResponse();
            // or  get the same positive  expectedResponse from testCase?
            CheckRegistrationResponseDto actualResponse = registrationApi
                    .checkRegistration(testCase.getCheckDto())
                    .statusCode(200)
                    .extract().as(CheckRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(CheckRegistrationNegativeCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    void negativeTestCase(CheckRegistrationNegativeCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () -> {
            CheckRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
            CheckRegistrationResponseDto actualResponse = registrationApi
                    .checkRegistration(testCase.getCheckDto())
                    .statusCode(422)
                    .extract().as(CheckRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

}

