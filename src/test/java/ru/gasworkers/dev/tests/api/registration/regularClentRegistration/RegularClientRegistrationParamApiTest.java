package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;
import ru.gasworkers.dev.api.registration.regularRegistration.RegularRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static ru.gasworkers.dev.model.apiModel.UserType.CLIENT;

@Owner("Igor Shingelevich")
public class RegularClientRegistrationParamApiTest extends BaseApiTest {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();
    static File expectedResponse = new File("src/test/resources/api/registration/startRegistration200Response.json");


    private static Stream<Arguments> registrationDataProviderPositive() {
        return Stream.of(
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), true, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), false, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), null, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), null, true, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), null, false, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), null, null, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, generateRandomPhone(), true, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, generateRandomPhone(), false, expectedResponse)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, generateRandomPhone(), null, expectedResponse))
        );
    }

    @ParameterizedTest
    @MethodSource("registrationDataProviderPositive")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tag(AllureTag.REGRESSION)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.REGISTRATION)
    @Tag(AllureTag.POSITIVE)
    @Tag(AllureTag.API)
    @DisplayName("Старт регистрации (позитивный кейс)")
    public void clientRegistrationPositiveApiTest(StartRegistrationInputDto inputDto) throws IOException {
        // Read expected response from the file
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));

        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(200)
                .extract().body().asString();

        // Compare actual response with expected response
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    private static Stream<Arguments> registrationDataProviderParamSetNegative() {
        return Stream.of(
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, true, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, false, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), null, null, null, new File ("src/test/resources/api/registration/negativeResponse/noEmailOrPhone422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), generateRandomPhone(), null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, generateRandomEmail(), null, null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), true, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), false, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json"))),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        null, null, generateRandomPhone(), null, new File("src/test/resources/api/registration/negativeResponse/noUserType422.json")))
        );
    }


    @ParameterizedTest
    @MethodSource("registrationDataProviderParamSetNegative")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tag(AllureTag.REGRESSION)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.REGISTRATION)
    @Tag(AllureTag.NEGATIVE)
    @Tag(AllureTag.API)
    @DisplayName("Старт регистрации (негативный кейс - комбинация параметров запроса)")
    public void clientRegistrationParamSetNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().body().asString();

        // Compare actual response with expected response
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }


    public List<String> invalidEmail() {
        List<String> invalidEmail = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/ru/gasworkers/dev/tests/registration/usualRegistration/usualClientRegistration/resources/registration/invalidEmailFormat.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 3) {
                    String email = data[0].trim();
                    invalidEmail.add(email);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return invalidEmail;
    }

    private static Stream<Arguments> registrationDataProviderEmailValidationNegative() {
        List<String> invalidEmailList = new RegularClientRegistrationParamApiTest().invalidEmail();
        List<Arguments> argumentsList = new ArrayList<>();
        for (String invalidEmail : invalidEmailList) {
            File expectedResponseFile = new File("src/test/resources/api/registration/negativeResponse/emailValidation422.json");
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, null, null, expectedResponseFile)));
            argumentsList.add(Arguments.of(StartRegistrationInputDto.newInstance(
                    CLIENT.toString(), invalidEmail, generateRandomPhone(), null, expectedResponseFile)));
        }
        return argumentsList.stream();
    }


    @ParameterizedTest
    @MethodSource("registrationDataProviderEmailValidationNegative")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tag(AllureTag.REGRESSION)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.REGISTRATION)
    @Tag(AllureTag.NEGATIVE)
    @Tag(AllureTag.API)
    @DisplayName("Старт регистрации (негативный кейс - валидация почты)")
    public void clientRegistrationParamNegativeApiTest(StartRegistrationInputDto inputDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponse = FileUtils.readFileToString(inputDto.getExpectedResponseFile(), String.valueOf(StandardCharsets.UTF_8));
        String actualResponse = registrationApi.startRegistration(inputDto)
                .statusCode(422)
                .extract().body().asString();

        // Compare actual response with expected response
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }



    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }

}
