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
import ru.gasworkers.dev.api.registration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi1;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.gasworkers.dev.model.Role.CLIENT;
import static ru.gasworkers.dev.model.Role.MASTER;

public class ParametrizedRegistrationStartApiTest extends BaseTest {
    public final RegularStartRegistrationApi1 regularStartRegistrationApi1 = new RegularStartRegistrationApi1();

    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();

    @Browser(role = CLIENT)
    ClientPages clientPages;
    @Browser(role = MASTER)
    MasterPages masterPages;
    // for Service use master role

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



    private static Stream<Arguments> registrationParameters() {
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
                Arguments.of(16, "client", generateRandomEmail(), true),
                Arguments.of(17, "client", generateRandomEmail(), false),
                Arguments.of(18, "client", generateRandomEmail(), null),
                Arguments.of(19, "client", "", true),
                Arguments.of(20, "client", "", false),
                Arguments.of(21, "client", "", null),
                // All combinations without email
                Arguments.of(22, "client", generateRandomPhone(), true),
                Arguments.of(23, "client", generateRandomPhone(), false),
                Arguments.of(24, "client", generateRandomPhone(), null),
                Arguments.of(25, "client", "", true),
                Arguments.of(26, "client", "", false),
                Arguments.of(27, "client", "", null),
                // All combinations with phone as empty string
                Arguments.of(28, "client", generateRandomEmail(), "", true),
                Arguments.of(29, "client", generateRandomEmail(), "", false),
                Arguments.of(30, "client", generateRandomEmail(), "", null),
                // All combinations with email as empty string
                Arguments.of(31, "client", "", generateRandomPhone(), true),
                Arguments.of(32, "client", "", generateRandomPhone(), false),
                Arguments.of(33, "client", "", generateRandomPhone(), null),
                // All combinations without isPhoneSend
                Arguments.of(34, "client", generateRandomEmail(), generateRandomPhone()),
                Arguments.of(35, "client", generateRandomEmail(), null),
                Arguments.of(36, "client", null, generateRandomPhone()),
                Arguments.of(37, "client", "", ""),
                Arguments.of(38, "client", generateRandomEmail(), ""),
                Arguments.of(39, "client", "", generateRandomPhone()),
                Arguments.of(40, "client", generateRandomEmail()),
                Arguments.of(41, "client", null),
                // The combination without phone, email, and isPhoneSend
                Arguments.of(42, "client")
        );
    }


    public static class RegistrationArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return registrationParameters().filter(arg -> {
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

                allCasesSet = "1-42";

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


    /*@ParameterizedTest
    @ArgumentsSource(RegistrationArgumentsProvider.class)
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.COMBINATORIAL), @Tag(AllureTag.API)})
    @DisplayName("Регистрация пользователя Api для роли: {0} с email: {1} и phone: {2} и employedStatus: {3}")
    void testRegularStartRegistration(int index, String userType, String email, String phone, Boolean isPhoneSend) {
        Response response = regularStartRegistrationApi1.regularStartRegistration(userType, email, phone, isPhoneSend);

        assertEquals(200, response.statusCode());
        assertEquals("0", response.jsonPath().getString("status"));
        assertEquals("Успешная регистрация", response.jsonPath().getString("message"));

        RandomClient randomClient = new RandomClient();
        String firstName = randomClient.getName();
        String lastName = randomClient.getSurname();
        String patronymicName = randomClient.getPatronymicName();
        String password = randomClient.getPassword();
        String code = randomClient.getConfirmationCode();
        String gender = Gender.MALE.toString();
        String employedStatus = Employed_status.SELF_EMPLOYED.toString();

        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, userType, email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(userType, email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        if (userType.equals(UserType.CLIENT.toString())) {
            clientPages.getLoginPage().open();
            clientPages.getLoginPage().loginEmail(email, password);
            clientPages.getHomePage().urlChecker.urlContains("profile/client");
        }
        if (userType.equals(UserType.MASTER.toString())) {
            masterPages.getLoginPage().open();
            masterPages.getLoginPage().loginEmail(email, password);
            masterPages.getHomePage().urlChecker.urlContains("profile/master");
        }
        if (userType.equals(UserType.SERVICE.toString())) {
            masterPages.getLoginPage().open();
            masterPages.getLoginPage().loginEmail(email, password);
            masterPages.getHomePage().urlChecker.urlContains("profile/service");
        }
    }*/
}





