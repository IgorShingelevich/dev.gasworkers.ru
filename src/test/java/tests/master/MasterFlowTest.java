package tests.master;

import extension.browser.Browser;
import model.client.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.context.MasterPages;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;
import static model.Role.*;

public class MasterFlowTest extends BaseTest {

    @Browser(role = MASTER)
    MasterPages masterPages;

    private final String
            emailDispatcher = "test_gas_master_sssr1@rambler.ru",
            passwordDispatcher = "123456";

    @BeforeEach
    void masterLogin() {
        masterPages.getLoginPage().open();
        masterPages.getLoginPage().login(emailDispatcher, passwordDispatcher);
    }

    @DisplayName("Мастер открывает заказ: ")
    @Test
    void checkMasterDispatchedOrderStatus(){
        step("Мастер открывает заказ в статусе: " + OrderStatus.MASTER_DISPATCHED, () -> {
            masterPages.getHomePage().checkFinishLoading();
            masterPages.getHomePage().sidebar.ordersHistoryDropdown();
            masterPages.getHomePage().sidebar.newOrders();
            masterPages.getNewOrdersPage().checkFinishLoading();
            masterPages.getNewOrdersPage().openOrderByNumber(3215);
            masterPages.getOrderCardPage().checkFinishLoading();
        });
    }


}
