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
import ru.gasworkers.dev.api.registration.RegularFinishMasterRegistrationApi;
import ru.gasworkers.dev.api.registration.RegularStartRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.MASTER;

public class RegularMasterRegistrationApiTest extends BaseTest {
    @Browser(role = MASTER)
    MasterPages masterPages;
    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }
    public final RegularStartRegistrationApi regularStartRegistrationApi = new RegularStartRegistrationApi();
    public final CheckRegistrationCode checkRegistrationCode = new CheckRegistrationCode();

    public final RegularFinishMasterRegistrationApi regularFinishMasterRegistrationApi = new RegularFinishMasterRegistrationApi();
    private final RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
    private final String
            email = randomSelfEmployedAndMaster.getEmail(),
            phone = randomSelfEmployedAndMaster.getPhone(),
            firstName = randomSelfEmployedAndMaster.getFirstName(),
            lastName = randomSelfEmployedAndMaster.getLastName(),
            patronymicName = randomSelfEmployedAndMaster.getPatronymicName(),
            password = randomSelfEmployedAndMaster.getPassword(),
            code = randomSelfEmployedAndMaster.getCode();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.MASTER), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация мастера Api ")
    public void regularMasterRegistration() {
        regularFinishMasterRegistrationApi.regularFinishMasterRegistration(email, phone, firstName, lastName, patronymicName, password);

        masterPages.getLoginPage().open();
        masterPages.getLoginPage().loginEmail(email, password);
    }

}

