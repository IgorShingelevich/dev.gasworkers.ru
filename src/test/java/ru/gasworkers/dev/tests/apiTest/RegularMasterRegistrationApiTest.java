package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.CheckRegistrationCode;
import ru.gasworkers.dev.api.registration.RegularFinishRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Employed_status;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.ServiceCompanyStaff;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.MASTER;

public class RegularMasterRegistrationApiTest extends BaseTest {
    @Browser(role = MASTER)
    MasterPages masterPages;

    @BeforeEach
    public void setUpRestAssured() {
        ApiTestConfig.configureRestAssured();
    }

    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();
    public final CheckRegistrationCode checkRegistrationCode = new CheckRegistrationCode();
    public final RegularFinishRegistrationApi regularFinishRegistrationApi = new RegularFinishRegistrationApi();
    public final ServiceCompanyStaff staff = new ServiceCompanyStaff();

    private final RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
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
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.MASTER), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация мастера Api ")
    public void regularMasterRegistration() {

        regularStartRegistrationApi.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
        checkRegistrationCode.checkRegistrationCode(code, UserType.MASTER.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.ACCEPTED.toString(), serviceId);
        masterPages.getLoginPage().open();
        masterPages.getLoginPage().loginEmail(email, password);
        masterPages.getHomePage().urlChecker.urlContains("profile/master");
    }

}

