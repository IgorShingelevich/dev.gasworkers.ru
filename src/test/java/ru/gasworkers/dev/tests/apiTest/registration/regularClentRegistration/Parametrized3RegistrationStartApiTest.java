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

public class Parametrized3RegistrationStartApiTest extends BaseTest {
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
                Arguments.of(1, "client", generateRandomEmail(), generateRandomPhone(), true),
                Arguments.of(2, "client", generateRandomEmail(), generateRandomPhone(), false),
                Arguments.of(3, "client", generateRandomEmail(), generateRandomPhone(), null),
                Arguments.of(4, "client", generateRandomEmail(), "", true),
                Arguments.of(5, "client", generateRandomEmail(), "", false),
                Arguments.of(6, "client", generateRandomEmail(), "", null),
                Arguments.of(7, "client", generateRandomEmail(), null, true),
                Arguments.of(8, "client", generateRandomEmail(), null, false),
                Arguments.of(9, "client", generateRandomEmail(), null, null),
                Arguments.of(10, "client", "", generateRandomPhone(), true),
                Arguments.of(11, "client", "", generateRandomPhone(), false),
                Arguments.of(12, "client", "", generateRandomPhone(), null),
                Arguments.of(13, "client", "", "", true),
                Arguments.of(14, "client", "", "", false),
                Arguments.of(15, "client", "", "", null),
                Arguments.of(16, "client", "", null, true),
                Arguments.of(17, "client", "", null, false),
                Arguments.of(18, "client", "", null, null),
                Arguments.of(19, "client", null, generateRandomPhone(), true),
                Arguments.of(20, "client", null, generateRandomPhone(), false),
                Arguments.of(21, "client", null, generateRandomPhone(), null),
                Arguments.of(22, "client", null, "", true),
                Arguments.of(23, "client", null, "", false),
                Arguments.of(24, "client", null, "", null),
                Arguments.of(25, "client", null, null, true),
                Arguments.of(26, "client", null, null, false),
                Arguments.of(27, "client", null, null, null),
                Arguments.of(28, "client", generateRandomEmail(), generateRandomPhone()),
                Arguments.of(29, "client", generateRandomEmail(), ""),
                Arguments.of(30, "client", generateRandomEmail(), null),
                Arguments.of(31, "client", "", generateRandomPhone()),
                Arguments.of(32, "client", "", ""),
                Arguments.of(33, "client", "", null),
                Arguments.of(34, "client", null, generateRandomPhone()),
                Arguments.of(35, "client", null, ""),
                Arguments.of(36, "client", null, null),
                Arguments.of(37, "client", generateRandomEmail(), true),
                Arguments.of(38, "client", generateRandomEmail(), false),
                Arguments.of(39, "client", generateRandomEmail(), null),
                Arguments.of(40, "client", "", true),
                Arguments.of(41, "client", "", false),
                Arguments.of(42, "client", "", null),
                Arguments.of(43, "client", null, true),
                Arguments.of(44, "client", null, false),
                Arguments.of(45, "client", null, null),
                Arguments.of(46, "client", true),
                Arguments.of(47, "client", false),
                Arguments.of(48, "client", null),
                Arguments.of(49, "client")

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

        allCasesSet ="1-48";

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

    @Tag(AllureTag.API)
    @Owner("owner")
    @ParameterizedTest(name = "Start regular registration{0} {1} {2} {3}")
    @ArgumentsSource(RegistrationArgumentsProvider.class)
    @DisplayName("test case with role:  {1}, email {2}, phone: {3), isPhoneSend: {4})")
    public void testRegularStartRegistration(int testCaseNumber, String userType, String email, String phone, Boolean isEnabled) {
        Optional<String> optionalEmail = Optional.ofNullable(email);
        Optional<String> optionalPhone = Optional.ofNullable(phone);
        Optional<Boolean> optionalIsEnabled = Optional.ofNullable(isEnabled);

        Response response = regularStartRegistrationApi.regularStartRegistration(userType, optionalEmail.orElse(null), optionalPhone.orElse(null), optionalIsEnabled.orElse(null));

        assertEquals(200, response.getStatusCode(), "Failed test case number: " + testCaseNumber);
    }

    public static class RegistrationParametersDataProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            List<Arguments> arguments = new ArrayList<>();

            registrationParametersDataProvider().forEach(arguments::add);

            return arguments.stream();
        }
    }
}