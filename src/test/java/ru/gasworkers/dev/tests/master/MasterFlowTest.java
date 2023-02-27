package ru.gasworkers.dev.tests.master;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.master.ReadyForVideoState;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.ClientBuilder;

import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.model.Role.*;

public class MasterFlowTest extends BaseTest {

    @Browser(role = MASTER)
    MasterPages masterPages;

    ClientBuilder master = new ClientBuilder(
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
        String checkedOrderNumber = "3911";
        masterPages.getHomePage().checkFinishLoading();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allOrdersHistoryDropdown();
        masterPages.getHomePage().sidebar.allNewOrders();
        masterPages.getAllNewOrdersPage().checkFinishLoading();
        masterPages.getAllNewOrdersPage().switchToListView();
        masterPages.getAllNewOrdersPage().openByNumber(checkedOrderNumber);
        masterPages.getOrderCardPage().checkFinishLoading();
        masterPages.getOrderCardPage().checkMasterDispatchedOrderState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);
        masterPages.getOrderCardPage().editObject();
        masterPages.getEditObjectPage().checkFinishLoading();
        masterPages.getEditObjectPage().navGasBranch();
        masterPages.getEditObjectPage().editDistributorTab.checkFinishLoading();
        //TODO check Distributor details
        masterPages.getEditObjectPage().editDistributorTab.editDistributor();
        //TODO check Distributor  Modal details
        masterPages.getEditObjectPage().editDistributorTab.distributorModal.checkFinishLoading(); // TODO fix modalTitleLocator
        masterPages.getEditObjectPage().editDistributorTab.distributorModal.cancel();
        masterPages.getEditObjectPage().editDistributorTab.checkFinishLoading();
        masterPages.getEditObjectPage().navObject();
        masterPages.getEditObjectPage().editObjectTab.checkFinishLoading();
        masterPages.getEditObjectPage().editObjectTab.toOrder();
        // TODO checkFinishLoading - expand  docs check, order properties check, price check
    }

    @Test
    @Feature("Кабинет мастера")
    @Story("Просмотр заказа на ТО")
    @Tags({@Tag("regression"), @Tag("master"), @Tag("cabinet"), @Tag("positive")})
    @DisplayName("Мастер редактирует чеклист")
    public void clientCheckFillUpCheckListState () {
        String checkedOrderNumber = "3911";
        masterPages.getHomePage().checkFinishLoading();
        masterPages.getHomePage().sidebar.checkReadyForVideoState(ReadyForVideoState.READY);
        masterPages.getHomePage().sidebar.allOrdersHistoryDropdown();
        masterPages.getHomePage().sidebar.allNewOrders();
        masterPages.getAllNewOrdersPage().checkFinishLoading();
        masterPages.getAllNewOrdersPage().switchToListView();
        masterPages.getAllNewOrdersPage().openByNumber(checkedOrderNumber);
        masterPages.getOrderCardPage().checkFinishLoading();
        masterPages.getOrderCardPage().startWork();
        masterPages.getOrderCardPage().checkFillingCheckListState(OrderStatus.MASTER_DISPATCHED, OrderType.MAINTENANCE);
//        masterPages.getOrderCardPage().navInfoMaster();
//        masterPages.getOrderCardPage().tabInfoMaster.table.checkDefaultState();
        masterPages.getOrderCardPage().navCheckList();
        masterPages.getOrderCardPage().tabCheckList.checkListComponent.checkEnabledDefaultState();

        //todo implement check checklist not exist
        // TODO checkFinishLoading - expand  docs check, order properties check, price check
    }

    @Disabled
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
        //TODO checkNotReviewedCompletedOrderState
//        masterPages.getOrderCardPage().checkNotReviewedCompletedOrderState(OrderStatus.COMPLETED, OrderType.MAINTENANCE);
        // TODO checkFinishLoading - expand  docs check, order properties check, price check
    }

}
// TODO submitReview flow
