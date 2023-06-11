/*
package ru.gasworkers.dev.tests.api.registration.regularServiceMasterRegistration;

import org.junit.jupiter.api.*;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.regular.CheckRegistrationCodeApi;
import ru.gasworkers.dev.api.registration.regular.FinishRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.Gender;
import ru.gasworkers.dev.model.apiModel.ServiceCompanyStaff;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.MASTER;

public class RegularServiceMasterRegistrationApiTest extends BaseTest {
    @Browser(role = MASTER)
    MasterPages masterPages;

    @BeforeEach
    public void setUpRestAssured() {
        ApiTestConfig.configureRestAssured();
    }

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

    */
/*@Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.MASTER), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация мастера Api ")
    public void regularMasterRegistration() {

        OLDRegularStartRegistrationApi.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.ACCEPTED.toString(), serviceId);
        masterPages.getLoginPage().open();
        masterPages.getLoginPage().loginEmail(email, password);
        masterPages.getHomePage().urlChecker.urlContains("profile/master");
    }*//*


}

*/
