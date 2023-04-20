package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi1;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parametrized2RegistrationStartApiTest extends BaseTest {
    public final RegularStartRegistrationApi1 regularStartRegistrationApi1 = new RegularStartRegistrationApi1();
    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    private static String generateRandomEmail() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getEmail();
    }

    private static String generateRandomPhone() {
        RandomClient randomClient = new RandomClient();
        return randomClient.getPhone();
    }


    private static Stream<Arguments> registrationParametersDataProvider() {
        return Stream.of(
                // All combinations with phone (including empty string)
                Arguments.of(1, "client", generateRandomEmail(), generateRandomPhone(), true),
                Arguments.of(2, "client", generateRandomEmail(), generateRandomPhone(), false),
                Arguments.of(3, "client", generateRandomEmail(), generateRandomPhone(), null),
                Arguments.of(4, "client", generateRandomEmail(), "", true),
                Arguments.of(5, "client", generateRandomEmail(), "", false),
                Arguments.of(6, "client", generateRandomEmail(), "", null),
                // All combinations with email (including empty string)
                Arguments.of(7, "client", "", generateRandomPhone(), true),
                Arguments.of(8, "client", "", generateRandomPhone(), false),
                Arguments.of(9, "client", "", generateRandomPhone(), null),
                // All combinations with phone as null
                Arguments.of(10, "client", generateRandomEmail(), null, true),
                Arguments.of(11, "client", generateRandomEmail(), null, false),
                Arguments.of(12, "client", generateRandomEmail(), null, null),
                // All combinations with email as null
                Arguments.of(13, "client", null, generateRandomPhone(), true),
                Arguments.of(14, "client", null, generateRandomPhone(), false),
                Arguments.of(15, "client", null, generateRandomPhone(), null),
                // All combinations without phone
                Arguments.of(16, "client", generateRandomEmail(), "", true),
                Arguments.of(17, "client", generateRandomEmail(), "", false),
                Arguments.of(18, "client", generateRandomEmail(), "", null),
                Arguments.of(19, "client", "", "", true),
                Arguments.of(20, "client", "", "", false),
                Arguments.of(21, "client", "", "", null),
                // All combinations without email
                Arguments.of(22, "client", "", generateRandomPhone(), true),
                Arguments.of(23, "client", "", generateRandomPhone(), false),
                Arguments.of(24, "client", "", generateRandomPhone(), null),
                Arguments.of(25, "client", "", "", true),
                Arguments.of(26, "client", "", "", false),
                Arguments.of(27, "client", "", "", null),
                // All combinations without phone and isPhoneSend
                Arguments.of(28, "client", generateRandomEmail(), "", null),
                Arguments.of(29, "client", "", "", null),
                // All combinations without email and isPhoneSend
                Arguments.of(30, "client", "", generateRandomPhone(), null),
                Arguments.of(31, "client", "", "", null),
                // All combinations without phone, email, and isPhoneSend
                Arguments.of(32, "client", "", "", null)
        );
    }


    public static class RegistrationArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return registrationParametersDataProvider().filter(arg -> {
                int index = (int) arg.get()[0]; // Assuming the index is the first element of the arguments
                for (int allowedCase : allowedCases) {
                    if (index == allowedCase) {
                        return true;
                    }
                }
                return false;
            });
        }

        String
                allPositiveCasesSet = "1-3, 10-12",
                negative422CasesSet = "13-15, 28-33",
        // cases  with error: No ParameterResolver registered for parameter [java.lang.Boolean arg4] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
        noParameterResolverArg4CasesSet = "18, 21, 24, 27,  34-39",

        // cases with error: No ParameterResolver registered for parameter [java.lang.String arg3] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
        noParameterResolverArg3CasesSet = "40, 41",

        // cases with error:No ParameterResolver registered for parameter [java.lang.String arg2] in method [void ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration.Parametrized2RegistrationStartApiTest.testRegularStartRegistration(int,java.lang.String,java.lang.String,java.lang.String,java.lang.Boolean)].
        noParameterResolverArg2CasesSet = "42",

        //cases with error: Error converting parameter at index 3: No implicit conversion to convert object of type java.lang.Boolean to type java.lang.String
        //org.junit.jupiter.api.extension.ParameterResolutionException: Error converting parameter at index 3: No implicit conversion to convert
        errorConvertingParameterAtIndex3 = "16, 17, 19, 20, 22, 23, 25, 26",

        allCasesSet ="1-32";

        private final int[] allowedCases = parseAllowedCases(allCasesSet);

        private int[] parseAllowedCases(String allowedCasesExpression) {
            String[] parts = allowedCasesExpression.split(",");
            List<Integer> allowedCasesList = new ArrayList<>();
            for (String part : parts) {
                part = part.trim();
                if (part.contains("-")) {
                    String[] range = part.split("-");
                    int start = Integer.parseInt(range[0].trim());
                    int end = Integer.parseInt(range[1].trim());
                    for (int i = start; i <= end; i++) {
                        allowedCasesList.add(i);
                    }
                } else {
                    allowedCasesList.add(Integer.parseInt(part));
                }
            }
            return allowedCasesList.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    @ParameterizedTest
    @ArgumentsSource(RegistrationArgumentsProvider.class)
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.COMBINATORIAL), @Tag(AllureTag.API)})
    @DisplayName("Регистрация пользователя Api кейс: {0} для роли: {1} с email: {2} и phone: {3} и isPhoneSend: {4}")
    void testRegularStartRegistration(int index, String userType, String email, String phone, Boolean isPhoneSend) {
        Response response = regularStartRegistrationApi.regularStartRegistration(userType, Optional.ofNullable(email), Optional.ofNullable(phone), Optional.ofNullable(isPhoneSend));
        assertEquals(200, response.statusCode());
        assertEquals("0", response.jsonPath().getString("status"));
        assertEquals("Успешная регистрация", response.jsonPath().getString("message"));
    }

}