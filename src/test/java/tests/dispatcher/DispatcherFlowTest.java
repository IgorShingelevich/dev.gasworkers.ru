package tests.dispatcher;

import extension.browser.Browser;
import org.junit.jupiter.api.*;
import pages.context.DispatcherPages;
import tests.TestBase;

import static model.Role.*;

import static io.qameta.allure.Allure.step;

public class DispatcherFlowTest extends TestBase {

    @Browser(role = DISPATCHER)
    DispatcherPages dispatcherPages;

    private final String
        emailDispatcher = "test_gw_dispatcher_sssr1@rambler.ru",
        passwordDispatcher = "123456";

    @BeforeEach
    void dispatcherLogin() {
        dispatcherPages.getLoginPage().open();
        dispatcherPages.getLoginPage().login(emailDispatcher, passwordDispatcher);
    }

    @Test
    @DisplayName("Диспетчере посещает разделы Сайта")
     void dispatcherPagesRoaming() {
        step("Диспетчере посещает разделы Сайта", () -> {
//            dispatcherPages.getLoginPage().open();
//            dispatcherPages.getLoginPage().login(emailDispatcher, passwordDispatcher);
            dispatcherPages.getHomePage().checkFinishLoading();

        });
    }

    @Test
    @DisplayName("Dispatcher - Open Current Order")
     void openCurrentOrder() {
        step("Dispatcher - Open Current Order", () -> {
            dispatcherPages.getHomePage().checkFinishLoading();
        });
    }
}
