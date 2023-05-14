package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.CheckRegularRegistrationCodeInputDto;
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class StartRegularClientRegistrationParamApiTest extends BaseApiTest {
    private final StartRegularRegistrationApi registrationApi = new StartRegularRegistrationApi();

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regularClentRegistration.RegistrationDataProvider#startRegistrationDataProviderPositive")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Старт регулярной регистрации client master service (позитивный кейс)")
    public void fromCheckClientStartRegularRegistrationPositiveApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
//        inputDto.getExpectedStartResponseFile(null);
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(200)
                .extract().body().asString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regularClentRegistration.RegistrationDataProvider#startRegistrationDataProviderPositive")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Старт регулярной регистрации client master service (позитивный кейс)")
    public void clientStartRegularRegistrationPositiveApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
        inputDto.setExpectedResponseFile(null);
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(200)
                .extract().body().asString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regularClentRegistration.RegistrationDataProvider#startRegistrationDataProviderEmailValidationNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации (негативный кейс - валидация почты)")
    public void clientStartRegularRegistrationParamNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
        inputDto.setExpectedResponseFile(null);
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().body().asString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }


    @ParameterizedTest
    @MethodSource("ru.gasworkers.dev.tests.api.registration.regularClentRegistration.RegistrationDataProvider#startRegistrationDataProviderParamSetNegative")
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Старт регулярной регистрации (негативный кейс - комбинация параметров запроса)")
    public void clientStartRegularRegistrationBodyCombinationsParamNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
        inputDto.setExpectedResponseFile(null);
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().body().asString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }
}
