package tests.client;

import extension.browser.Browser;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.client.ClientRequestType;
import model.client.OrderStatus;
import model.client.OrderType;
import org.junit.jupiter.api.*;
import pages.context.ClientPages;
import tests.BaseTest;
import utils.User;
import utils.RandomClient;

import static io.qameta.allure.Allure.step;

import static model.Role.*;

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
    @Feature("Новый заказ на ТО ")
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
        step("Убедиться, что тип Заказа {orderTypeIs}", () -> {
            OrderType orderType = OrderType.MAINTENANCE;
            clientPages.getOrderCardPage().checkOrderType(orderType);
            // compare .checkOrderType and .checkOrderStatus what is better? more universal?
        });
        step("Убедиться, что статус Заказа {orderStatusIs}", () -> {
            clientPages.getOrderCardPage().checkOrderStatusNew(OrderStatus.NEW_ORDER);
            // how to make .checkOrderStatus  universal and also check corresponded buttons
        });

    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Просмотр заказа")
    @DisplayName("Клиент просматривает заказ в кабинете")
    public void clientCheckOrderStatus() {
        Integer checkedOrderNumber = 3185;
        step("Убедиться, что статус Заказа: " + checkedOrderNumber + " является: " + OrderStatus.SCHEDULE_VISIT, () -> {
            OrderStatus orderStatus = OrderStatus.SCHEDULE_VISIT;
            clientPages.getHomePage().checkFinishLoading();
            clientPages.getHomePage().sidebar.clickOrdersAndInvoicesDropdown();
            clientPages.getHomePage().sidebar.allOrders();
            clientPages.getAllOrdersPage().checkFinishLoading();
            clientPages.getAllOrdersPage().orderByNumber(checkedOrderNumber);
            clientPages.getOrderCardPage().checkFinishLoading();
            clientPages.getOrderCardPage().checkOrderStatusScheduleVisit(orderStatus);
        });
    }

   /* @Test
    public void checkNewSms() {
        step("Проверить, что появилось новое сообщение", () ->
                client.firstCodeFromNewSMS());
    }*/

    @Test
    @Feature("Кабинет клиента")
    @Story("Заполненный кабинет ")
    @DisplayName("Выбранный клиент просматривает заполненный кабинет")
    public void checkFilledCabinetState(){
        clientPages.getHomePage().checkFinishLoading();
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        clientPages.getProfilePage().navCommon();
        clientPages.getProfilePage().commonTab.checkFilledCabinetState();
        clientPages.getProfilePage().commonTab.checkInitialStatus(client00.name, client00.patronymic, client00.surname);
        clientPages.getProfilePage().navContacts();
        clientPages.getProfilePage().contactsTab.checkFinishLoading();
        clientPages.getProfilePage().contactsTab.checkFilledStatus(client00.email, String.valueOf(client00.phoneNumber));
        //TODO password and Notifications
    }

    @Test
    @Feature("Кабинет клиента")
    @Story("Незаполненный ( начальное состояние) кабинет")
    @DisplayName("Клиент наполняет свой Профиль в первый раз")
    public void fillingUpInitialClientProfilePage() {
        clientPages.getHomePage().sidebar.profile();
        clientPages.getProfilePage().checkFinishLoading();
        clientPages.getProfilePage().navCommon();
        clientPages.getProfilePage().commonTab.checkInitialCabinetState();
        //TODO check  fullName fill up address and passport
        clientPages.getProfilePage().navContacts();
        clientPages.getProfilePage().contactsTab.checkFinishLoading();
        clientPages.getProfilePage().contactsTab.checkFilledStatus(randomClient.getEmail(), randomClient.getPhone());
        //TODO password and Notifications

    }

}
