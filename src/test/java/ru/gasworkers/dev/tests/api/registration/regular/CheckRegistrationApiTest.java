package ru.gasworkers.dev.tests.api.registration.regular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.registration.regular.check.CheckRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckRegistrationApiTest {

    RandomClient randomClient = new RandomClient();
    String phone = randomClient.getPhone();
    String email = randomClient.getEmail();
    String type = UserType.CLIENT.toString();
    private final StartRegularRegistrationApi startRegularRegistrationApi = new StartRegularRegistrationApi();
    private final CheckRegularRegistrationCodeApi checkRegistrationApi = new CheckRegularRegistrationCodeApi();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ComplexRegistrationCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Проверка кода регуляроной регистрации -  200 OK")
    public void clientCheckRegistrationPositiveApiTest(ComplexRegistrationCase testCase) {
        step("Start registration", () ->
                startRegularRegistrationApi.startRegistration(testCase.getStartRequest())
                        .statusCode(200));

        step("Check registration", () -> {
            CheckRegistrationResponseDto expectedResponse = CheckRegistrationResponseDto.successResponse();
            CheckRegistrationResponseDto actualResponse = checkRegistrationApi
                    .checkRegularRegistrationCode(testCase.getCheckRequest())
                    .statusCode(200)
                    .extract().as(CheckRegistrationResponseDto.class);
            assertCheckRegistrationResponse(expectedResponse, actualResponse);
        });
    }

    @Test
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName(" Проверка кода  регулярной регистрации клиента - 422 неверный код")
    public void clientCheckRegularRegistrationWrongCodeNegativeApiTest() throws IOException {
        startRegularRegistrationApi.startRegistration(type, email, phone, true);
        CheckRegistrationResponseDto expectedResponse = CheckRegistrationResponseDto.wrongCodeResponse();
        CheckRegistrationResponseDto actualResponse = checkRegistrationApi.checkRegularRegistrationCode(0, type, email, phone)
                .statusCode(422)
                .extract().as(CheckRegistrationResponseDto.class);

        assertCheckRegistrationResponse(expectedResponse, actualResponse);
    }

        private void assertCheckRegistrationResponse(CheckRegistrationResponseDto expectedResponse, CheckRegistrationResponseDto actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


}

