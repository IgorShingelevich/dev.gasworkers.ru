package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.CheckRegularRegistrationCodeInputDto;
import ru.gasworkers.dev.api.registration.regularRegistration.StartRegularRegistrationApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REGISTRATION)
@Feature(AllureFeature.REGULAR_REGISTRATION)
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.REGISTRATION)
@Tag(AllureTag.API)
public class CheckClientRegularRegistrationCodeParamApiTest {
    private final StartRegularRegistrationApi registrationApi = new StartRegularRegistrationApi();

//    @ParameterizedTest
//    @MethodSource
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.POSITIVE)
//    @DisplayName(" Проверка кода  регулярной регистрации клиента (позитивный кейс)")
//    public void clientCheckRegularRegistrationPositiveApiTest(CheckRegularRegistrationCodeInputDto inputCheckDto) throws IOException {
//        String expectedResponse = FileUtils.readFileToString(inputCheckDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
//        inputDto.setExpectedResponseFile(null);
//        String actualResponse = registrationApi.startRegistration(inputDto)
//
//    }
}
