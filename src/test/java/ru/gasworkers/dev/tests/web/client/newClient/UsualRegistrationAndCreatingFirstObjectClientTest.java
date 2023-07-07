package ru.gasworkers.dev.tests.web.client.newClient;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.tests.BaseTest;
@Deprecated
@Owner("Igor Shingelevich")
@Feature("Регистрация")
@Story("Регистрация клиента с перехожом  на страницу создания объекта")
@Tags({@Tag("regression"), @Tag("client"), @Tag("registration"), @Tag("positive")})
public class UsualRegistrationAndCreatingFirstObjectClientTest extends BaseTest {
    @Test
    @DisplayName("Регистрация - гид  -  создание объекта")
    void registrationClientInitialGuideDialogCreate() {
        //TODO check initial guide leading to create an object
    }
}
