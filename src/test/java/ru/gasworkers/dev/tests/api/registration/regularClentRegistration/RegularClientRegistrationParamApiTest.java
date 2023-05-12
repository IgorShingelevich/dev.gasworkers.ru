package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.regularRegistration.RegularStartRegistrationApi;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularClientRegistrationParamApiTest extends BaseTest {

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    private final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();

    private static Stream<Arguments> registrationDataProvider() {
        return Stream.of(
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), false),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), null),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), "noArg", true),
                createArguments(UserType.CLIENT.toString(), "noArg", generateRandomPhone(), true),
                createArguments("noArg", generateRandomEmail(), generateRandomPhone(), true),
                createArguments(null, generateRandomEmail(), generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), generateRandomEmail(), null, true),
                createArguments(UserType.CLIENT.toString(), null, generateRandomPhone(), true),
                createArguments(UserType.CLIENT.toString(), null, null, false)
        );
    }

    private static Arguments createArguments(String userType, String email, String phone, Boolean isPhoneSend) {
        return Arguments.of(userType, email, phone, isPhoneSend);
    }

    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }


    @ParameterizedTest
    @MethodSource("registrationDataProvider")
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tag(AllureTag.REGRESSION)
    @Tag(AllureTag.CLIENT)
    @Tag(AllureTag.REGISTRATION)
    @Tag(AllureTag.POSITIVE)
    @Tag(AllureTag.API)
    @DisplayName("Регистрация {0}  Api с параметрами, {1}, {2}, {3}")
    public void clientRegistrationParamApiTest(String userType, String email, String phone, Boolean isPhoneSend) throws JsonProcessingException {
        Map<String, Object> requestBodyMap = new HashMap<>();
        if ( userType != "noArg" ) {
            requestBodyMap.put("type", userType);
        }
        if ( email != "noArg" ) {
            requestBodyMap.put("email", email);
        }
        if ( phone != "noArg" ) {
            requestBodyMap.put("phone", phone);
        }
        if ( isPhoneSend != null ) {
            requestBodyMap.put("phone_send", isPhoneSend);
        }

        // Perform the API call
        regularStartRegistrationApi.regularStartRegistration(requestBodyMap);

        // Add your assertions here
    }


































//    @ParameterizedTest
//    @ArgumentsSource(BaseStartRegistrationTestCasesProvider.class)
//    @Owner("Igor Shingelevich")
//    @Epic(AllureEpic.REGISTRATION)
//    @Feature(AllureFeature.REGULAR_REGISTRATION)
//    @Tag(AllureTag.REGRESSION)
//    @Tag(AllureTag.CLIENT)
//    @Tag(AllureTag.REGISTRATION)
//    @Tag(AllureTag.POSITIVE)
//    @Tag(AllureTag.API)
//    @DisplayName("Регистрация {0}  Api с параметрами, {1}, {2}, {3}")
//    public void clientAndMasterRegistrationParamApiTest(String userType, String email, String phone, Boolean isPhoneSend) throws JsonProcessingException {
//        Map<String, Object> requestBodyMap = new HashMap<>();
//        if ( userType != "noArg" ) {
//            requestBodyMap.put("type", userType);
//        }
//        if ( email != "noArg" ) {
//            requestBodyMap.put("email", email);
//        }
//        if ( phone != "noArg" ) {
//            requestBodyMap.put("phone", phone);
//        }
//        if ( isPhoneSend != null ) {
//            requestBodyMap.put("phone_send", isPhoneSend);
//        }
//
//        // Perform the API call
//        regularStartRegistrationApi.regularStartRegistration(requestBodyMap);
//
//        // Add your assertions here
//    }


}
