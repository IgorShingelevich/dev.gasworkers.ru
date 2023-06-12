package ru.gasworkers.dev.tests.api.registration.regular.finish;

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
import ru.gasworkers.dev.api.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Story("Завершение регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class FinishRegistrationApiTest extends BaseApiTest {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(FinishRegistrationPositiveCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(FinishRegistrationPositiveCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () ->
                registrationApi.checkRegistration(testCase.getCheckDto())
                        .statusCode(200));

        step("Finish registration", () -> {
            FinishRegistrationResponseDto expectedResponse = FinishRegistrationResponseDto.successResponse();
            FinishRegistrationResponseDto actualResponse = registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(200)
                    .extract().as(FinishRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(FinishRegistrationNegativeCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    void negativeTestCase(FinishRegistrationNegativeCase testCase) {
        step("Start registration", () ->
                registrationApi.startRegistration(testCase.getStartDto())
                        .statusCode(200));

        step("Check registration", () ->
                registrationApi.checkRegistration(testCase.getCheckDto())
                        .statusCode(200));

        step("Finish registration", () -> {
            /*registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(422);*/
            FinishRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
            FinishRegistrationResponseDto actualResponse = registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(422)
                    .extract().as(FinishRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);

        });
    }

}
