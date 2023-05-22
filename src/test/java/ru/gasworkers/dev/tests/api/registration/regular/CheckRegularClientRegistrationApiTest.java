package ru.gasworkers.dev.tests.api.registration.regular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.registration.CheckRegularRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regularRegistration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.io.IOException;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckRegularClientRegistrationApiTest {

    RandomClient randomClient = new RandomClient();
    String phone = randomClient.getPhone();
    String email = randomClient.getEmail();
    String type = UserType.CLIENT.toString();
    private final StartRegularRegistrationApi startRegularRegistrationApi = new StartRegularRegistrationApi();
    private final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName(" Проверка кода  регулярной регистрации клиента - 422 неверный код")
    public void clientCheckRegularRegistrationWrongCodeNegativeApiTest() throws IOException {
        startRegularRegistrationApi.startRegistration(type, email, phone, true);
        CheckRegularRegistrationResponseDto expectedResponse = CheckRegularRegistrationResponseDto.wrongCodeResponse();
        CheckRegularRegistrationResponseDto actualResponse = checkRegularRegistrationCodeApi.checkRegularRegistrationCode(0, type, email, phone)
                .statusCode(422)
                .extract().as(CheckRegularRegistrationResponseDto.class);

        assertCheckRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.CheckRegistrationDataProvider#checkRegistrationDataProviderPositive")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Проверка кода регуляроной регистрации -  200 OK")
    public void clientCheckRegularRegistrationPositiveApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        startRegularRegistrationApi.startRegistration(inputDto);
        CheckRegularRegistrationResponseDto expectedResponse = CheckRegularRegistrationResponseDto.successResponse();
        CheckRegularRegistrationResponseDto actualResponse = checkRegularRegistrationCodeApi.checkRegularRegistrationCode(111111, inputDto.getType(), inputDto.getEmail(), inputDto.getPhone())
                .statusCode(200)
                .extract().as(CheckRegularRegistrationResponseDto.class);

        assertCheckRegistrationResponse(expectedResponse, actualResponse);
    }

        private void assertCheckRegistrationResponse(CheckRegularRegistrationResponseDto expectedResponse, CheckRegularRegistrationResponseDto actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }


}

