package ru.gasworkers.dev.tests.api.auth.registration.through.start;

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
import ru.gasworkers.dev.api.auth.registration.regular.RegularRegistrationApi;
import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.BG_REGISTRATION)
@Story("Начало фоновой регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class StartThroughRegistrationApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(StartThroughRegistrationPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(StartThroughRegistrationPositiveCase testCase) throws IOException {
        step("Start through registration", () -> {
            StartThroughRegistrationResponseDto actualResponse = registrationApi.startThroughRegistration(testCase.getStartDto())
                    .statusCode(200)
                    .extract().as(StartThroughRegistrationResponseDto.class);
            StartThroughRegistrationResponseDto expectedResponse = StartThroughRegistrationResponseDto.successResponse();
            assertResponse(expectedResponse, actualResponse);
        });
    }
}
