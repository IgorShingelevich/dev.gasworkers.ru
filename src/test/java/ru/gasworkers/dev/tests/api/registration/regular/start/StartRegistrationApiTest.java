package ru.gasworkers.dev.tests.api.registration.regular.start;

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
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationResponseDto;
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
        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.successResponse("60");
        StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(testCase.getStartDto())
                .statusCode(200)
                .extract().as(StartRegistrationResponseDto.class);
        assertResponse(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @EnumSource(StartRegistrationNegativeCase.class)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    public void negativeTestCase(StartRegistrationNegativeCase testCase) throws IOException {
        step("Start registration", () -> {
            StartRegistrationResponseDto expectedResponse = testCase.getExpectedResponse();
            StartRegistrationResponseDto actualResponse = registrationApi.startRegistration(testCase.getStartDto())
//                    .statusCode(testCase.getStatusCode())
                    .statusCode(422)
                    .extract().as(StartRegistrationResponseDto.class);
            assertResponse(expectedResponse, actualResponse);
        });
//
    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneValidationNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации - 422 валидация телефона")
//    public void clientStartRegularRegistrationPhoneValidationNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneInvalidErrorResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderPhoneAlreadyExistNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации - 422  существующий телефон")
//    public void clientStartRegularRegistrationPhoneAlreadyExistNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.phoneAlreadyExistsResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderTypeParamMissingNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации -  422 отсутствует тип пользователя")
//    public void clientStartRegularRegistrationMissingTypeNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.typeMissingResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderEmailAndPhoneParamMissingNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации -  422  отсутствует  почта или телефон")
//    public void clientStartRegularRegistrationMissingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailOrPhoneMissingResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderExistingEmailAndPhoneNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации -  422  существующая почта и телефон")
//    public void clientStartRegularRegistrationExistingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.emailAndPhoneAlreadyExistResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }
//
//    @ParameterizedTest
//    @MethodSource("ru.gasworkers.dev.tests.api.registration.regular.RegistrationDataProvider#startRegistrationDataProviderNoTypeExistingEmailAndPhoneNegative")
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.NEGATIVE)
//    @DisplayName("Старт регулярной регистрации -  422  нет типа пользователя, существующая почта и телефон")
//    public void clientStartRegularRegistrationNoTypeExistingEmailAndPhoneNegativeApiTest(StartRegistrationRequestDto inputDto) throws IOException {
//        StartRegistrationResponseDto expectedResponse = StartRegistrationResponseDto.noTypeEmailAndPhoneAlreadyExistsResponse();
//        StartRegistrationResponseDto actualResponse = startRegistrationApi.startRegistration(inputDto)
//                .statusCode(422)
//                .extract().as(StartRegistrationResponseDto.class);
//
//        assertResponse(expectedResponse, actualResponse);
//    }

}
