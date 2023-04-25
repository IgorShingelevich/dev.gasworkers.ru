package ru.gasworkers.dev.tests.apiTest.registration.regularClentRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi1;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class RegularEmailAndPhoneClientRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi1 regularStartRegistrationApi1 = new RegularStartRegistrationApi1();
    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();
    private final RandomClient randomClient = new RandomClient();
    private final String
            email = randomClient.getEmail(),
            phone = randomClient.getPhone(),
            firstName = randomClient.getName(),
            lastName = randomClient.getSurname(),
            patronymicName = randomClient.getPatronymicName(),
            password = randomClient.getPassword(),
            code = randomClient.getConfirmationCode(),
            gender = Gender.MALE.toString(),
            employedStatus = Employed_status.SELF_EMPLOYED.toString(); // status of employment for client not defined in api
@Order(1)
    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (email, phone, true)")
    public void clientRegistrationEmailAndPhoneApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), email, phone, true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Order(2)
    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api ('', phone, true)")
    public void clientRegistrationEmailAndPhoneNullApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), null, phone,true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, null);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Order(3)
    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (null, phone, true)")
    public void clientRegistrationPhoneApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), null, phone,true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), null, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (email, null, true)")
    public void clientRegistrationEmailAndPhoneFalseApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), email, null,true);
       /* checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, null);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (email, '', true)")
    public void clientRegistrationEmailApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), email, "", true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (email, true)")
    public void clientRegistrationEmailOnlyApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), email, true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регистрация клиента Api (phone)")
    public void clientRegistrationPhoneToEmailApiTest() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.CLIENT.toString(), phone, true);
        /*checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.CLIENT.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.CLIENT.toString(), email, phone, firstName, lastName, patronymicName, password, gender, false, false, null, null);
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
        clientPages.getHomePage().urlChecker.urlContains("profile/client");*/
    }

}
