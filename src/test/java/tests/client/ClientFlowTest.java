package tests.client;

import extension.browser.Browser;
import model.client.ClientRequestType;
import model.client.OrderStatus;
import model.client.OrderType;
import org.junit.jupiter.api.*;
import pages.context.ClientPages;
import tests.BaseTest;
import utils.User;

import static io.qameta.allure.Allure.step;

import static model.Role.*;

class ClientFlowTest extends BaseTest {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    User client = new User(
            "Игорь",
            "Сергеевич",
            "Шингелевич",
            "shingelevich@gmail.com",
            "123456",
            null,
            79288010225L
    );

    @BeforeEach
        void clientLogin() {
            clientPages.getLoginPage().open();
            clientPages.getLoginPage().login(client.email, client.password);
    }

    @Test
    void checkFioInformation() {
        step("Проверка ФИО", () -> {
            clientPages.getHomePage().checkFio("Шингелевич Игорь Сергеевич");
        });

    }

    @Test
    public void ClientPlaceMaintenanceRequestAndCancel() {
        step("Убедиться, что Домашняя страница загружена", () -> {
            clientPages.getHomePage()
                    .checkFinishLoading();
        });
        step(" Нажать кнопку Создать Заказ", () ->
            clientPages.getHomePage().clickPlaceOrderButton()
        );
        step("Выбрать тип заказа {ClientRequestType}", () ->
            clientPages.getTypeOrdersPage()
                    .selectOrderType(ClientRequestType.MAINTENANCE) //  .toString()
        );
        step("Под информацией нажать кнопку Далее", () ->
            clientPages.getInfoTypeOrderPage()
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
                    .clickNextButton()
        );
        step("Выбрать объект ТО", () ->
            clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0)
        );
        step("Указать Сегодня время и дату", () ->
            clientPages.getSelectDateMaintenancePage().pickNowDateAM()
        );
        step("Нажать кнопку Разместить Заказ", () ->
            clientPages.getSelectDateMaintenancePage().submitOrder()
        );
        step("Убедиться, что страница Выбор СК загружена ", () ->
            clientPages.getSelectServicePage().checkFinishLoading()
        );
        step("Нажать на кнопку Смотреть Заказ", () ->
            clientPages.getSelectServicePage().toOrderCard()
        );
        step("Закрыть всплывающие уведомления", () ->
            clientPages.getSelectServicePage().popUpClose()
        );
        step("Убедиться, что страница Заказ загружена", () ->
            clientPages.getOrderCardPage().checkFinishLoading()
        );
        step("Нажать на кнопку Отменить Заказ", () ->
            clientPages.getOrderCardPage().cancelOrder()
        );
        step("Убедиться, что страница Отмена Заказа загружена", () ->
            clientPages.getCancelOrderPage().checkFinishLoading()
        );
        step("Нажать на кнопку Назад", () ->
            clientPages.getCancelOrderPage().backButton()
        );
        step("Убедиться, что страница Заказ загружена", () ->
            clientPages.getOrderCardPage().checkFinishLoading()
        );
        step("Нажать на  кнопку Показать на карте", () ->
            clientPages.getOrderCardPage().showOnMap()
        );
        step("Убедиться, что страница страница Выбор СК загружена", () ->
            clientPages.getSelectServicePage().checkFinishLoading()
        );
        step("Нажать на кнопку Смотреть Заказ", () ->
            clientPages.getSelectServicePage().toOrderCard()
        );
        step("Убедиться, что страница Заказ загружена", () ->
            clientPages.getOrderCardPage().checkFinishLoading()
        );
        step("Нажать на кнопку Отменить Заказ", () ->
            clientPages.getOrderCardPage().cancelOrder()
        );
        step("Убедиться, что страница Отмена Заказа загружена", () ->
            clientPages.getCancelOrderPage().checkFinishLoading()
        );
        step("Нажать на кнопку Да, Отменить", () ->
            clientPages.getCancelOrderPage().yesButton()
        );
        step("Убедиться, что Домашняя страница загружена", () ->
            clientPages.getHomePage().checkFinishLoading()
        );
    }

    @Test
    public void ClientPlaceMaintenanceRequest() {
        clientPages.getHomePage().checkFinishLoading();
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
    public void clientCheckOrderStatus() {
        Integer checkedOrderNumber = 3185;
        step("Убедиться, что статус Заказа: " + checkedOrderNumber + " является: "  + OrderStatus.SCHEDULE_VISIT, () -> {
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

    @Test
    public void checkNewSms() {
        step("Проверить, что появилось новое сообщение", () -> {
            client.firstCodeFromNewSMS();

        });
    }


}


