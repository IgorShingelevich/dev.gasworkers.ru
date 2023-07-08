package ru.gasworkers.dev.tests.api.auth.registration.regular.start;

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
import ru.gasworkers.dev.api.auth.registration.regular.dto.start.StartRegistrationResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Story("Старт регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class StartRegistrationApiTest extends BaseApiTest {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(StartRegistrationPositiveCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(StartRegistrationPositiveCase testCase) throws IOException {
        StartRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.successResponse("60");
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(testCase.getStartDto())
                .statusCode(200)
                .extract().as(StartRegistrationResponseDto.class);
        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(StartRegistrationNegativeCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    public void negativeTestCase(StartRegistrationNegativeCase testCase) throws IOException {
        step("Start registration", () -> {
            StartRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
            StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(testCase.getStartDto())
                    .statusCode(422)
                    .extract().as(StartRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
    }

}
