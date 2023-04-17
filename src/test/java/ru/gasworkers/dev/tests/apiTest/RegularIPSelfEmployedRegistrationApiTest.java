package ru.gasworkers.dev.tests.apiTest;

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
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.ServiceCompanyStaff;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

public class RegularIPSelfEmployedRegistrationApiTest {
    @Browser(role = SELF_EMPLOYED)
    SelfEmployedPages selfEmployedPages;

    @BeforeEach
    public void setUpRestAssured() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();
    public final CheckRegularRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegularRegistrationCodeApi();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();
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
    @DisplayName(" Регистрация самозанятого Api ")
    public void regularSelfEmployedRegistration() {
        regularStartRegistrationApi.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.SELF_EMPLOYED.toString(), null);


        selfEmployedPages.getLoginPage().open();
        selfEmployedPages.getLoginPage().loginEmail(email, password);
        selfEmployedPages.getHomePage().urlChecker.urlContains("profile/master");
    }


}
