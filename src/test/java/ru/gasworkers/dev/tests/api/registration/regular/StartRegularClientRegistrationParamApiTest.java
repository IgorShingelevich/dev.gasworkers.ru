package ru.gasworkers.dev.tests.api.registration.regular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationInputDto;
import ru.gasworkers.dev.api.registration.dto.registration.StartRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class StartRegularClientRegistrationParamApiTest extends BaseApiTest {
    //Arrange-Act-Assert (AAA) pattern
    private final StartRegularRegistrationApi registrationApi = new StartRegularRegistrationApi();
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPositive")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Старт регулярной регистрации client master service 200 OK")
    public void clientStartRegularRegistrationPositiveApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.successResponse("60");
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(200)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderEmailValidationNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422 валидация почты")
    public void clientStartRegularRegistrationEmailValidationNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailInvalidErrorResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneValidationNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422 валидация телефона")
    public void clientStartRegularRegistrationPhoneValidationNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneInvalidErrorResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneAlreadyExistNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422  существующий телефон")
    public void clientStartRegularRegistrationPhoneAlreadyExistNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneAlreadyExistsResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderTypeParamMissingNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422 отсутствует тип пользователя")
    public void clientStartRegularRegistrationMissingTypeNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.typeMissingResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderEmailAndPhoneParamMissingNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  отсутствует  почта или телефон")
    public void clientStartRegularRegistrationMissingEmailAndPhoneNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailOrPhoneMissingResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderExistingEmailAndPhoneNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  существующая почта и телефон")
    public void clientStartRegularRegistrationExistingEmailAndPhoneNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailAndPhoneAlreadyExistResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderNoTypeExistingEmailAndPhoneNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  нет типа пользователя, существующая почта и телефон")
    public void clientStartRegularRegistrationNoTypeExistingEmailAndPhoneNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.noTypeEmailAndPhoneAlreadyExistsResponse();
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertStartRegistrationResponse(expectedResponse, actualResponse);
    }

    private void assertStartRegistrationResponse(StartRegistrationResponseDto expectedResponse, StartRegistrationResponseDto actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }
}
