package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.ServiceCompanyStaff;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import java.util.stream.Stream;

import static ru.gasworkers.dev.model.Role.CLIENT;
import static ru.gasworkers.dev.model.Role.MASTER;

public class ParametrizedRegularRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;
    @Browser(role = MASTER)
    MasterPages masterPages;
    // for Service use master role

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();
    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();
    private final RandomClient randomClient = new RandomClient();
    public final ServiceCompanyStaff serviceCompanyStaff = new ServiceCompanyStaff();

    public static Stream<Arguments> registrationTestCases() {
        RandomClient randomClient = new RandomClient();
        RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();

        // Define specific email and phone variables for Master role
        String emailMaster = randomSelfEmployedAndMaster.getEmail();
        String phoneMaster = randomSelfEmployedAndMaster.getPhone();

        // Define specific email and phone variables for Client role
        String emailClient = randomClient.getEmail();
        String phoneClient = randomClient.getPhone();

        // Define specific email and phone variables for Service role
        String emailService = "service@example.com";
        String phoneService = "555-1234";

        return Stream.of(

                // Client test cases
                Arguments.of("Client", emailClient, phoneClient, false),
                Arguments.of("Client", emailClient, phoneClient, true),
                Arguments.of("Client", emailClient, null, false),
                Arguments.of("Client", emailClient, null, true),
                Arguments.of("Client", null, phoneClient, false),
                Arguments.of("Client", null, phoneClient, true),
                Arguments.of("Client", null, null, false),
                Arguments.of("Client", null, null, true),
                Arguments.of("Client", emailClient, phoneClient, null),
                Arguments.of("Client", emailClient, null, null),
                Arguments.of("Client", null, phoneClient, null),
                Arguments.of("Client", emailClient, null, null),
                Arguments.of("Client", null, phoneClient, null),
                Arguments.of("Client", null, null, null),

                // Master test cases
                Arguments.of("Master", emailMaster, phoneMaster, false),
                Arguments.of("Master", emailMaster, phoneMaster, true),
                Arguments.of("Master", emailMaster, null, false),
                Arguments.of("Master", emailMaster, null, true),
                Arguments.of("Master", null, phoneMaster, false),
                Arguments.of("Master", null, phoneMaster, true),
                Arguments.of("Master", null, null, false),
                Arguments.of("Master", null, null, true),
                Arguments.of("Master", emailMaster, phoneMaster, null),
                Arguments.of("Master", emailMaster, null, null),
                Arguments.of("Master", null, phoneMaster, null),
                Arguments.of("Master", emailMaster, null, null),
                Arguments.of("Master", null, phoneMaster, null),
                Arguments.of("Master", null, null, null),

                // Service company test cases
                Arguments.of("Service", emailService, phoneService, false),
                Arguments.of("Service", emailService, phoneService, true),
                Arguments.of("Service", emailService, null, false),
                Arguments.of("Service", emailService, null, true),
                Arguments.of("Service", null, phoneService, false),
                Arguments.of("Service", null, phoneService, true),
                Arguments.of("Service", null, null, false),
                Arguments.of("Service", null, null, true),
                Arguments.of("Service", emailService, phoneService, null),
                Arguments.of("Service", emailService, null, null),
                Arguments.of("Service", null, phoneService, null),
                Arguments.of("Service", emailService, null, null),
                Arguments.of("Service", null, phoneService, null),
                Arguments.of("Service", null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("registrationTestCases")
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tag("regression")
    @Tag("registration")
    @Tag("positive")
    @Tag("api")
    @DisplayName("Регистрация пользователя Api для роли: {0} с email: {1} и phone: {2} и employedStatus: {3}")
    public void rolesRegistrationApiTest(String userType, String email, String phone, Boolean isSelfEmployed) {
        String firstName = randomClient.getName();
        String lastName = randomClient.getSurname();
        String patronymicName = randomClient.getPatronymicName();
        String password = randomClient.getPassword();
        String code = randomClient.getConfirmationCode();
        String gender = Gender.MALE.toString();
        String employedStatus = Employed_status.SELF_EMPLOYED.toString();

        regularStartRegistrationApi.regularStartRegistration(userType, email, phone, true);
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
    }

}

/*
*   @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация пользователя Api ")
    public void rolesRegistrationApiTestForEachVersion() {
        Stream<Arguments> registrationTestCases = registrationTestCases(email, phone);
        registrationTestCases.forEach(args -> {
            String userType = (String) args.get()[0];
            String email = (String) args.get()[1];
            String phone = (String) args.get()[2];
            boolean isPhoneSend = (Boolean) args.get()[3];
        regularStartRegistrationApi.regularStartRegistration(userType, email, phone, true);
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
        if( userType.equals(UserType.SERVICE.toString())){
            masterPages.getLoginPage().open();
            masterPages.getLoginPage().loginEmail(email, password);
            masterPages.getHomePage().urlChecker.urlContains("profile/service");
        }
    }
    );
    }
* */

/*
public static Stream<Arguments> registrationTestCases() {
        RandomClient randomClient = new RandomClient();
        String emailClient = randomClient.getEmail();
        String phoneClient = randomClient.getPhone();
        RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
        String emailMaster = randomSelfEmployedAndMaster.getEmail();
        String phoneMaster = randomSelfEmployedAndMaster.getPhone();
        // for service use emailMaster and phoneMaster
        return Stream.of(
                Arguments.of("Master", email, phone, false),
                Arguments.of("Master", email, phone, true),
                Arguments.of("Master", email, null, false),
                Arguments.of("Master", email, null, true),
                Arguments.of("Master", null, phone, false),
                Arguments.of("Client", email, null, null),
                Arguments.of("Client", null, phone, null),
                Arguments.of("Client", email, null, null),
                Arguments.of("Client", null, phone, null),
                Arguments.of("Client", null, null, null),
                Arguments.of("Service", email, phone, false),
                Arguments.of("Service", email, phone, true),
                Arguments.of("Service", email, null, null),
                Arguments.of("Service", null, phone, null),
                Arguments.of("Service", email, null, null),
                Arguments.of("Service", null, phone, null),
                Arguments.of("Service", null, null, null)
        );
    }
* */

