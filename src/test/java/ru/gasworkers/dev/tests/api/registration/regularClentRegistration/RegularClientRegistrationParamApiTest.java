package ru.gasworkers.dev.tests.api.registration.regularClentRegistration;

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
import ru.gasworkers.dev.api.registration.dto.StartRegistrationInputDto;
import ru.gasworkers.dev.api.registration.regularRegistration.RegularRegistrationApi;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static ru.gasworkers.dev.model.apiModel.UserType.CLIENT;

@Owner("Igor Shingelevich")
public class RegularClientRegistrationParamApiTest extends BaseApiTest {

    private final RegularRegistrationApi registrationApi = new RegularRegistrationApi();

    private static Stream<Arguments> registrationDataProviderPositive() {
        return Stream.of(
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), true)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), false)),
                Arguments.of(StartRegistrationInputDto.newInstance(
                        CLIENT.toString(), generateRandomEmail(), generateRandomPhone(), null))
//                createArguments(CLIENT.toString(), generateRandomEmail(), "noArg", true),
//                createArguments(CLIENT.toString(), "noArg", generateRandomPhone(), true),
//                createArguments("noArg", generateRandomEmail(), generateRandomPhone(), true),
//                createArguments(null, generateRandomEmail(), generateRandomPhone(), true),
//                createArguments(CLIENT.toString(), generateRandomEmail(), null, true),
//                createArguments(CLIENT.toString(), null, generateRandomPhone(), true),
//                createArguments(CLIENT.toString(), null, null, false)
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
    public void clientRegistrationParamApiTest(StartRegistrationInputDto inputDto) {
        registrationApi.startRegistration(inputDto)
                .statusCode(200)
                .body("status", is(0))
                .body("message", is("Успешная регистрация"))
                .body("data.seconds_left", is("60"));
    }

//     @ParameterizedTest
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

    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }

}
