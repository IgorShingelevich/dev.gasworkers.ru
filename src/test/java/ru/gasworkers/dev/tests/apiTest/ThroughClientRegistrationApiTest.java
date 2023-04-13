package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureStory;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.registration.ThroughClientRegistrationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class ThroughClientRegistrationApiTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;
    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }
    public final ThroughClientRegistrationApi throughClientRegistrationApi = new ThroughClientRegistrationApi();
    public final RandomClient randomClient = new RandomClient();
    private final String
            email = randomClient.getEmail(),
            phone = randomClient.getPhone(),
            description = randomClient.getDescription();

    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.REGISTRATION)
    @Feature(AllureFeature.BG_REGISTRATION)
    @Story(AllureStory.REPAIR)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.BG_REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Фоновая Регистрация клиента Api на Ремонт с указанием телефона и почты")
    public void throughClientRegistration() {
        throughClientRegistrationApi.repairThroughRegistration(email, phone, description);

        clientPages.getLoginPage().open();
        clientPages.getLoginPage().loginEmail(email, "1111");
    }

}
