package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.RegularFinishSelfEmployedRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

public class RegularFinishSelfEmployedRegistrationApiTest {
    @Browser(role = SELF_EMPLOYED)
    SelfEmployedPages selfEmployedPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    private final RegularFinishSelfEmployedRegistrationApi regularFinishSelfEmployedRegistrationApi = new RegularFinishSelfEmployedRegistrationApi();
    private final RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
    private final String
            email = randomSelfEmployedAndMaster.getEmail(),
            phone = randomSelfEmployedAndMaster.getPhone(),
            firstName = randomSelfEmployedAndMaster.getFirstName(),
            lastName = randomSelfEmployedAndMaster.getLastName(),
            patronymicName = randomSelfEmployedAndMaster.getPatronymicName(),
            password = randomSelfEmployedAndMaster.getPassword();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_INDIVIDUAL), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName(" Регистрация самозанятого Api ")
    public void regularSelfEmployedRegistration() {
        regularFinishSelfEmployedRegistrationApi.regularFinishSelfEmployedRegistration(email, phone, firstName, lastName, patronymicName, password);

        selfEmployedPages.getLoginPage().open();
        selfEmployedPages.getLoginPage().loginEmail(email, password);
    }


}
