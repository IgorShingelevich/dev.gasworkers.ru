package ru.gasworkers.dev.tests.master;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.browser.Browser;
import ru.gasworkers.dev.model.client.OrderState;
import ru.gasworkers.dev.model.client.OrderType;
import ru.gasworkers.dev.model.master.ReadyForVideoState;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.User;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.*;

public class MasterFlowTest extends BaseTest {

    @Browser(role = MASTER)
    MasterPages masterPages;

    User master = new User(
            "Мастер1СССР",
            "Мастерович1СССР",
            "Мастеров1СССР",
            " test_gas_master_sssr1@rambler.ru",
            "123456",
            null,
            79917644241L
    );



    @BeforeEach
    void masterLogin() {
        masterPages.getLoginPage().open();
        masterPages.getLoginPage().login(master.email, master.password);
    }


    @Owner("Igor Shingelevich")
    @Feature("Кабинет мастера")
    @Story("Просмотр заказа")
    @Tags({@Tag("regression"), @Tag("master"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("Мастер открывает заказ в статусе Мастер в пути")
    @Test
    void checkMasterDispatchedOrderStatus(){
        int orderNumber = 3215;
        OrderType orderType = OrderType.MAINTENANCE;

        masterPages.getHomePage().checkFinishLoading();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allNewOrders();
        masterPages.getAllNewOrdersPage().checkFinishLoading();
        masterPages.getAllNewOrdersPage().switchToListView();
        masterPages.getAllNewOrdersPage().openOrderByNumber(orderNumber);
        masterPages.getOrderCardPage().checkFinishLoading();
         masterPages.getOrderCardPage().checkOrderStateMasterDispatched(OrderState.MASTER_DISPATCHED);
         masterPages.getOrderCardPage().checkOrderType(OrderType.MAINTENANCE);
        // TODO checkFinishLoading - expand  docs check, order properties check, price check
    }

    @Owner("Igor Shingelevich")
    @Disabled
    @Feature("Кабинет мастера")
    @Story("Незаполненный ( начальное состояние) кабинет")
    @Tags({@Tag("regression"), @Tag("master"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("Мастер открывает кабинет в первый раз")
    @Test
    void checkInitialCabinetState(){
//       TODO masterPages.getHomePage().checkInitialState();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allNewOrders();
//        TODO masterPages.getAllNewOrdersPage().checkInitialState();
        masterPages.getHomePage().sidebar.allScheduledOrders();
//        TODO masterPages.getAllScheduledOrdersPage().checkInitialState();
        masterPages.getHomePage().sidebar.allCompletedOrders();
//        TODO masterPages.getAllCompletedOrdersPage().checkInitialState();
        masterPages.getHomePage().sidebar.resume();
//        TODO masterPages.getResumePage().checkInitialState();
        masterPages.getHomePage().sidebar.invitations();
//        TODO masterPages.getInvitationsPage().checkInitialState();
        masterPages.getHomePage().sidebar.profile();
//        TODO masterPages.getProfilePage().checkInitialState();
    }





}
