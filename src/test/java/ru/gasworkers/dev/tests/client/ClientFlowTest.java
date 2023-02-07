package ru.gasworkers.dev.tests.client;

import ru.gasworkers.dev.browser.Browser;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import ru.gasworkers.dev.model.client.ClientRequestType;
import ru.gasworkers.dev.model.client.OrderState;
import ru.gasworkers.dev.model.client.OrderType;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.User;
import ru.gasworkers.dev.utils.RandomClient;

import static io.qameta.allure.Allure.step;

import static ru.gasworkers.dev.model.Role.*;

class ClientFlowTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    User client00 = new User(
            "Игорь",
            "Сергеевич",
            "Шингелевич",
            "shingelevich@gmail.com",
            "123456",
            null,
            70012223344L);

    RandomClient randomClient = new RandomClient();

    @BeforeEach
    void clientLogin() {
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().login(client00.email, client00.password);
    }



    @Test
    @Feature("Новый заказ ТО")
    @Story("Создание заказа и его отмена")
    @DisplayName("Клиент создает заказ и отменяет его")
    public void clientPlaceMaintenanceRequestAndCancel() {
        clientPages.getHomePage().checkFinishLoading();
        clientPages.getHomePage().popUpClose();
        clientPages.getHomePage().clickPlaceOrderButton();
        clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
        clientPages.getInfoTypeOrderPage()
//                  .checkTitle("Заказ на ТО")
//                  .checkStepSequence("Шаг 1 из 3")
                        .clickNextButton();
        clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
        clientPages.getSelectDateMaintenancePage().pickNowDateAM();
        clientPages.getSelectDateMaintenancePage().submitOrder();
        clientPages.getSelectServicePage().checkFinishLoading();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getSelectServicePage().popUpClose();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().popUpClose();
        clientPages.getOrderCardPage().cancelOrder();
        clientPages.getCancelOrderPage().checkFinishLoading();
        clientPages.getCancelOrderPage().backButton();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().showOnMap();
        clientPages.getSelectServicePage().checkFinishLoading();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getOrderCardPage().cancelOrder();
        clientPages.getCancelOrderPage().checkFinishLoading();
        clientPages.getCancelOrderPage().yesButton();
        clientPages.getHomePage().checkFinishLoading();
    }

    @Test
    @Feature("Новый заказ ТО")
    @Story("Создание заказа")
    @DisplayName("Клиент создает заказ")
    public void clientPlaceMaintenanceRequest() {
        clientPages.getHomePage().checkFinishLoading();
        clientPages.getHomePage().popUpClose();
        clientPages.getHomePage().clickPlaceOrderButton();
        clientPages.getTypeOrdersPage().selectOrderType(ClientRequestType.MAINTENANCE); //  .toString()
        clientPages.getInfoTypeOrderPage()
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
                .clickNextButton();
        clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);
        clientPages.getSelectDateMaintenancePage().pickNowDateAM();
        clientPages.getSelectDateMaintenancePage().submitOrder();
        clientPages.getSelectServicePage().checkFinishLoading();
        clientPages.getSelectServicePage().toOrderCard();
        clientPages.getOrderCardPage().checkFinishLoading();
        clientPages.getSelectServicePage().popUpClose();
        clientPages.getOrderCardPage().checkOrderType(OrderType.MAINTENANCE);
        clientPages.getOrderCardPage().checkOrderStateNew(OrderState.NEW_ORDER);

    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа")
    @DisplayName("Клиент просматривает заказ в состоянии Согласование даты заказа")
    public void clientCheckScheduleVisitOrderSate () {
        String checkedOrderNumber = "3532";
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().sidebar.clickOrdersAndInvoicesDropdown();
            clientPages.getHomePage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkFinishLoading();
            clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkOrderStatusScheduleVisit(OrderState.SCHEDULE_VISIT);
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа")
    @DisplayName("Клиент просматривает заказ в состоянии Мастер в пути")
    public void clientCheckMasterDispatchedOrderSate () {
        String checkedOrderNumber = "3535";
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().sidebar.clickOrdersAndInvoicesDropdown();
            clientPages.getHomePage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkFinishLoading();
            clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkOrderStatusScheduleVisit(OrderState.MASTER_DISPATCHED);
    }



    @Test
    @Feature("Кабинет клиента")
    @Story("Заполненный кабинет ")
    @DisplayName("Выбранный клиент просматривает заполненный кабинет")
    public void checkFilledCabinetState(){
        clientPages.getHomePage().checkFinishLoading();
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        step("Вкладка Общие данные", () -> {
            clientPages.getProfilePage().navCommon();
            clientPages.getProfilePage().navCommonTab.checkFilledState();  // TODO implement CommonDataPickerComponent. Upload photo. check other fields info.
        });
        step("Вкладка Контакты", () -> {
            clientPages.getProfilePage().navContacts();
            clientPages.getProfilePage().navContactsTab.checkFinishLoading(client00.email, String.valueOf(client00.phoneNumber));
            clientPages.getProfilePage().navContactsTab.checkFilledState(client00.email, String.valueOf(client00.phoneNumber));
        });
        //TODO password and Notifications
    }

    @Test
    @Disabled
    @Feature("Кабинет клиента")
    @Story("Незаполненный ( начальное состояние) кабинет")
    @DisplayName("Клиент наполняет свой Профиль в первый раз")
    public void fillingUpInitialClientProfilePage() {
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        step("Вкладка Общие данные", () -> {
        clientPages.getProfilePage().navCommon();
        clientPages.getProfilePage().navCommonTab.checkFinishLoading();
        //TODO check  fullName fill up address and passport
        });
        step("Вкладка Контакты", () -> {
        clientPages.getProfilePage().navContacts();
        clientPages.getProfilePage().navContactsTab.checkFinishLoading(client00.email, String.valueOf(client00.phoneNumber));
        clientPages.getProfilePage().navContactsTab.checkFilledState(client00.email, String.valueOf(client00.phoneNumber));
        //TODO password and Notifications
        });
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Смена пароля")
    @DisplayName("Клиент изменяет свой пароль")
    public void changePasswordClientProfilePage() {
            clientPages.getHomePage().sidebar.profile();
            clientPages.getProfilePage().checkFinishLoading();
            clientPages.getProfilePage().navPassword();
            clientPages.getProfilePage().navPasswordTab.checkFinishLoading();
            clientPages.getProfilePage().navPasswordTab.checkInitialState();
            clientPages.getProfilePage().navPasswordTab.generatePassword();
    }

}
