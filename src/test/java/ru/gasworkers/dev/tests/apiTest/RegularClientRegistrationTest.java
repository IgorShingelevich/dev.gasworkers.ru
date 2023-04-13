package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.RegularClientRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class RegularClientRegistrationTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;
    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }
    public final RegularClientRegistrationApi regularClientRegistrationApi = new RegularClientRegistrationApi();
    public final RandomClient randomClient = new RandomClient();
    private final String
            email = randomClient.getEmail(),
            phone = randomClient.getPhone(),
            firstName = randomClient.getName(),
            lastName = randomClient.getSurname(),
            patronymicName = randomClient.getPatronymicName(),
            password = randomClient.getPassword();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.REGULAR_REGISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Регулярная регистрация клиента Api")
    public void regularClientRegistration() {
        regularClientRegistrationApi.regularClientRegistration(randomClient.getEmail(), randomClient.getPhone(), randomClient.getName(), randomClient.getSurname(), randomClient.getPatronymicName(), randomClient.getPassword());

        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, password);
    }
}
