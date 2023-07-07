package ru.gasworkers.dev.tests.api.auth.registration.through.check;

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
import ru.gasworkers.dev.api.auth.registration.through.dto.check.CheckThroughRegistrationResponseDto;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.BG_REGISTRATION)
@Story("Проверка фоновой регистрации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckThroughRegistrationApiTest extends BaseApiTest {
    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(CheckThroughRegistrationPositiveCase.class)
    @DisplayName("Success case:")
    void positiveTestCase(CheckThroughRegistrationPositiveCase testCase) {
        step("Start through registration", () -> {
            registrationApi.startThroughRegistration(testCase.getStartDto())
                    .statusCode(200);
        });
        step("Check through registration", () -> {
            CheckThroughRegistrationResponseDto actualResponse = registrationApi.checkThroughRegistration(testCase.getCheckDto())
                    .statusCode(200)
                    .extract().as(CheckThroughRegistrationResponseDto.class);
            String token = actualResponse.getData().getToken();
            Integer orderId = actualResponse.getData().getId();
            String orderType = actualResponse.getData().getType();
            CheckThroughRegistrationResponseDto expectedResponse = CheckThroughRegistrationResponseDto.successResponse(orderId, orderType, token);
            assertResponse(expectedResponse, actualResponse);
        });
    }
}

