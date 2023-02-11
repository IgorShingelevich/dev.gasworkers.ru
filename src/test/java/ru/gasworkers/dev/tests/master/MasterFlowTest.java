package ru.gasworkers.dev.tests.master;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
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
            "Зарегистрирован с 11 января 2023 года",
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

    @Test
    @Feature("Кабинет мастера")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("master"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("Мастер открывает заказ в состоянии Мастер в пути")
    public void clientCheckMasterDispatchedOrderSate () {
        String checkedOrderNumber = "3678";
        masterPages.getHomePage().checkFinishLoading();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allOrdersHistoryDropdown();
        masterPages.getHomePage().sidebar.allNewOrders();
        masterPages.getAllNewOrdersPage().checkFinishLoading();
        masterPages.getAllNewOrdersPage().switchToListView();
        masterPages.getAllNewOrdersPage().openByNumber(checkedOrderNumber);
        masterPages.getOrderCardPage().checkFinishLoading();
        masterPages.getOrderCardPage().checkMasterDispatchedOrderState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);
        // TODO checkFinishLoading - expand  docs check, order properties check, price check

    }

    @Owner("Igor Shingelevich")
    @Feature("Кабинет мастера")
    @Story("Просмотр заказа ТО")
    @Tags({@Tag("regression"), @Tag("master"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("Мастер открывает заказ в состоянии без Отзыва Завершен")
    @Test
    void checkNotReviewedCompletedOrderState(){
        String orderNumber = "3620";
        masterPages.getHomePage().checkFinishLoading();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allNewOrders();
        masterPages.getAllNewOrdersPage().checkFinishLoading();
        masterPages.getAllNewOrdersPage().switchToListView();
        masterPages.getAllNewOrdersPage().openByNumber(orderNumber);
        masterPages.getOrderCardPage().checkFinishLoading();
        masterPages.getOrderCardPage().checkMasterDispatchedOrderState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);
        masterPages.getOrderCardPage().checkOrderType(OrderType.MAINTENANCE);
        // TODO checkFinishLoading - expand  docs check, order properties check, price check
    }

}
// TODO submitReview flow
