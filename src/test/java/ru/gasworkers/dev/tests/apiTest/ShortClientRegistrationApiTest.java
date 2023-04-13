package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.ShortClientRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class ShortClientRegistrationApiTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;
    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }
    public final ShortClientRegistrationApi shortClientRegistrationApi = new ShortClientRegistrationApi();
    private final RandomClient randomClient = new RandomClient();
    private final String
            email = randomClient.getEmail(),
            phone = randomClient.getPhone();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Короткая регистрация клиента Api( как проверить на UI ?)")
    public void shortClientRegistrationApiTest() {
        shortClientRegistrationApi.shortClientRegistration(email, phone);

//        clientPages.getLoginPage().open();
//        clientPages.getLoginPage().loginEmail(email, "1111"); // what password
    }

}
