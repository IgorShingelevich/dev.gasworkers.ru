package ru.gasworkers.dev.tests.apiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.authorisation.AuthorizationApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.pages.context.ClientPages;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class AuthorizationApiTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final AuthorizationApi authorizationApi = new AuthorizationApi();
    private final String
            userType = UserType.CLIENT.toString(),
            email = "shingelevich@gmail.com",
            phone = "79119129233",
            password = "123456";


    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.AUTHORIZATION)
    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.CLIENT), @Tag(AllureTag.AUTHORISATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Авторизация клиента  по почте Api")
    public void clientEmailAuthorization() {
        authorizationApi.authorization(null, email, null, null, password);

    }


}
