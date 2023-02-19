package ru.gasworkers.dev.tests.client.newClient;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.tests.BaseTest;

public class UsualRegistrationAndCreatingFirstObjectClientTest extends BaseTest {

    @Test
    @Disabled
    @Owner("Igor Shingelevich")
    @Order(1)
    @Feature("Регистрация")
    @Story("Регистрация клиента с перехожом  на страницу создания объекта")
    @Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
    @DisplayName("Регистрация - гид  -  создание объекта")
    void registrationClientInitialGuideDialogCreate() {
        //TODO check initial guide leading to create an object
    }
}
