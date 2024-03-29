package ru.gasworkers.dev.tests.api.auth.registration.regular.finish;

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
import ru.gasworkers.dev.api.auth.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.finish.FinishRegistrationResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Story("Завершение регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class FinishRegistrationApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    @ParameterizedTest(name = "{0}")
    @EnumSource(FinishRegistrationPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Positive case:")
    void positiveTestCase(FinishRegistrationPositiveCase testCase) {
        startAndCheckPrecondition(testCase.getStartDto(), testCase.getCheckDto());
        step("Finish registration", () -> {
            FinishRegistrationResponseDto expectedResponse = FinishRegistrationResponseDto.successResponse();
            FinishRegistrationResponseDto actualResponse = registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(testCase.getExpectedStatusCode())
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
        startAndCheckPrecondition(testCase.getStartDto(), testCase.getCheckDto());
        step("Finish registration", () -> {
            FinishRegistrationResponseDto actualResponse = registrationApi.finishRegistration(testCase.getFinishDto())
                    .statusCode(testCase.getExpectedStatusCode())
                    .extract().as(FinishRegistrationResponseDto.class);
            FinishRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
            assertResponse(expectedResponse, actualResponse);
        });
    }

    private void startAndCheckPrecondition(StartRegistrationRequestDto startDto, CheckRegistrationRequestDto checkDto) {
        step("Start registration", () ->
                registrationApi.startRegistration(startDto)
                        .statusCode(200));

        step("Check registration", () ->
                registrationApi.checkRegistration(checkDto)
                        .statusCode(200));
    }
}
