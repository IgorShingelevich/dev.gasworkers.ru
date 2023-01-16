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
    @DisplayName("Dispatcher - Pages Roaming")
     void dispatcherPagesRoaming() {
        step("Dispatcher -  check the order in the list", () -> {
//            dispatcherPages.getLoginPage().open();
//            dispatcherPages.getLoginPage().login(emailDispatcher, passwordDispatcher);
            dispatcherPages.getHomePage().isOpened();

        });
    }

    @Test
    @DisplayName("Dispatcher - Open Current Order")
     void openCurrentOrder() {
        step("Dispatcher - Open Current Order", () -> {
            dispatcherPages.getHomePage().isOpened();
        });
    }
}
