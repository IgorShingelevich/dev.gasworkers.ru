package ru.gasworkers.dev.tests.web.dispatcher;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.tests.BaseTest;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.DISPATCHER;

@Disabled
public class DispatcherFlowTest extends BaseTest {

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
    @Step("Диспетчер открывает заказ в статусе: ")
    @Test
     void checkMasterDispatchedOrderStatus() {
        step("Диспетчер открывает заказ в статусе: " + OrderStatus.MASTER_DISPATCHED, () -> {
            dispatcherPages.getHomePage().checkFinishLoading();
            dispatcherPages.getHomePage().switchToListView();
            dispatcherPages.getHomePage().navInProgress();
            dispatcherPages.getHomePage().openOrderByNumber("3192");
            dispatcherPages.getOrderCardPage().checkFinishLoading();
            dispatcherPages.getOrderCardPage().checkMasterDispatchedState(OrderStatus.MASTER_DISPATCHED, ServiceType.MAINTENANCE);
            dispatcherPages.getOrderCardPage().selectAnotherTime();
            dispatcherPages.getOrderCardPage().datePicker.selectNowDateAndTime();
            dispatcherPages.getOrderCardPage().selectAnotherMaster();
            dispatcherPages.getSelectMasterPage().checkFinishLoading();
            dispatcherPages.getSelectMasterPage().selectAvailableMasterByIndex(0);
            dispatcherPages.getOrderCardPage().checkFinishLoading();







        });
    }
}
