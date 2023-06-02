package ru.gasworkers.dev.tests.api.registration.regular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationResponseDto;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class StartRegistrationApiTest extends BaseApiTest {
    //Arrange-Act-Assert (AAA) pattern
    private final StartRegistrationApi startRegistrationApi = new StartRegistrationApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ComplexRegistrationCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Старт регулярной регистрации client master service 200 OK")
    public void clientStartRegistrationPositiveApiTest(ComplexRegistrationCase testCase) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.successResponse("60");
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(testCase.getStartRequest())
                .statusCode(200)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderEmailValidationNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422 валидация почты")
    public void clientStartRegularRegistrationEmailValidationNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailInvalidErrorResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneValidationNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422 валидация телефона")
    public void clientStartRegularRegistrationPhoneValidationNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneInvalidErrorResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneAlreadyExistNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации - 422  существующий телефон")
    public void clientStartRegularRegistrationPhoneAlreadyExistNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneAlreadyExistsResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderTypeParamMissingNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422 отсутствует тип пользователя")
    public void clientStartRegularRegistrationMissingTypeNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.typeMissingResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderEmailAndPhoneParamMissingNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  отсутствует  почта или телефон")
    public void clientStartRegularRegistrationMissingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailOrPhoneMissingResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderExistingEmailAndPhoneNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  существующая почта и телефон")
    public void clientStartRegularRegistrationExistingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailAndPhoneAlreadyExistResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderNoTypeExistingEmailAndPhoneNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации -  422  нет типа пользователя, существующая почта и телефон")
    public void clientStartRegularRegistrationNoTypeExistingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.noTypeEmailAndPhoneAlreadyExistsResponse();
        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().as(StartRegistrationResponseDto.class);

        assertResponse(expectedResponse, actualResponse);
    }

}
