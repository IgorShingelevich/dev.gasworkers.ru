package ru.gasworkers.dev.tests.apiTest.registration.regularSelfEmployedRegistration;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.CheckRegularRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.ConfigureSelfEmployedRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi1;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.ServiceCompanyStaff;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

public class RegularSelfEmployedRegistrationApiTest {
    /*@Browser(role = SELF_EMPLOYED)
    SelfEmployedPages selfEmployedPages;

    @BeforeEach
    public void setUpRestAssured() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi1 regularStartRegistrationApi1 = new RegularStartRegistrationApi1();
    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();
    public final ConfigureSelfEmployedRegistrationApi configureSelfEmployedRegistrationApi = new ConfigureSelfEmployedRegistrationApi();
    private final RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
    public final ServiceCompanyStaff staff = new ServiceCompanyStaff();

    private final String
            email = randomSelfEmployedAndMaster.getEmail(),
            phone = randomSelfEmployedAndMaster.getPhone(),
            firstName = randomSelfEmployedAndMaster.getFirstName(),
            lastName = randomSelfEmployedAndMaster.getLastName(),
            patronymicName = randomSelfEmployedAndMaster.getPatronymicName(),
            password = randomSelfEmployedAndMaster.getPassword(),
            code = randomSelfEmployedAndMaster.getCode(),
            gender = Gender.MALE.toString();
    private final Integer
            serviceId = staff.EGIDA.ID;

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_INDIVIDUAL), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация самозанятого Api ( без конфигурации) - получается Рекрут? Чем отличается от регистрации мастера в ск? " )
    public void noConfigSelfEmployedRegistration() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.SELF_EMPLOYED.toString(), null);

        selfEmployedPages.getLoginPage().open();
        selfEmployedPages.getLoginPage().loginEmail(email, password);
        selfEmployedPages.getHomePage().urlChecker.urlContains("profile/master");
    }
    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_INDIVIDUAL), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация самозанятого Api ( c конфигурацией ")
    public void configSelfEmployedRegistration() {
        regularStartRegistrationApi1.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.SELF_EMPLOYED.toString(), null);
        configureSelfEmployedRegistrationApi.configureSelfEmployedRegistration(false, true);


        selfEmployedPages.getLoginPage().open();
        selfEmployedPages.getLoginPage().loginEmail(email, password);
        selfEmployedPages.getHomePage().urlChecker.urlContains("profile/master");
    }*/


}
