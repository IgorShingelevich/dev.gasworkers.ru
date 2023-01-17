package tests.client;

import extension.browser.Browser;
import model.client.ClientOrderType;
import org.junit.jupiter.api.*;
import pages.context.ClientPages;
import tests.TestBase;

import static io.qameta.allure.Allure.step;

import static model.Role.*;

class ClientFlowTest extends TestBase {
    @Browser(role = CLIENT)
    ClientPages clientPages;

    private final String
        emailClient = "shingelevich@gmail.com",
        clientName = "Шингелевич Игорь Сергеевич",
        passwordClient = "123456";

    @DisplayName("Вход в личный кабинет клиента")
    @BeforeEach
        void clientLogin() {
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().login(emailClient, passwordClient);
    }

    @Test
    @DisplayName( "Проверка ФИO")
    void checkFioInformation() {
        step("Проверка ФИО", () -> {
            clientPages.getHomePage().checkFio(clientName);
        });

    }


    @DisplayName("Клиент создает и отменяет заказ на ТО")
//    @Disabled
    @Test
    public void ClientPlaceMaintenanceRequestAndCancel() {
        step("Убедиться, что Домашняя страница загружена", () -> {
            clientPages.getHomePage()
                    .checkFinishLoading();
        });
        step(" Нажать кнопку Создать Заказ", () ->
            clientPages.getHomePage().clickPlaceOrderButton()
        );
        step("Выбрать тип заказа {ClientOrderType}", () ->
            clientPages.getTypeOrdersPage()
                    .selectOrderType(ClientOrderType.MAINTENANCE) //  .toString()
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
            clientPages.getSelectServicePage().toOrder()
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
            clientPages.getSelectServicePage().toOrder()
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

    @DisplayName("Клиент создает и  заказ на ТО")
//    @Disabled
    @Test
    public void ClientPlaceMaintenanceRequest() {
        step("Убедиться, что Домашняя страница загружена", () -> {
            clientPages.getHomePage()
                    .checkFinishLoading();
        });
        step(" Нажать кнопку Создать Заказ", () ->
                clientPages.getHomePage().clickPlaceOrderButton()
        );
        step("Выбрать тип заказа {ClientOrderType}", () ->
                clientPages.getTypeOrdersPage()
                        .selectOrderType(ClientOrderType.MAINTENANCE) //  .toString()
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
                clientPages.getSelectServicePage().toOrder()
        );
        step("Убедиться, что страница Заказ загружена", () ->
                clientPages.getOrderCardPage().checkFinishLoading()
        );
        step("Закрыть всплывающие уведомления", () ->
                clientPages.getSelectServicePage().popUpClose()
        );
    }


//
//    @DisplayName("openRandomObject")
//    @Test
//    void openRandomObject() {
//        homeClientPage.sidebarClient.allObjects();
//        allObjectsClientPage.openRandomObject();
//        objectCardClientPage.isOpened();
//        objectCardClientPage.sidebarClient.allObjects();
//        allObjectsClientPage.isOpened();
//        allObjectsClientPage.openRandomObject();
//        objectCardClientPage.isOpened();
//        objectCardClientPage.sidebarClient.allObjects();
//        allObjectsClientPage.isOpened();
//        allObjectsClientPage.openRandomObject();
//        objectCardClientPage.isOpened();
//        objectCardClientPage.sidebarClient.home();
//    }
//
//    @Description("ClientPlaceRequest")
//    //    @Disabled
//    @Test
//    void placeMaintenanceFirstObject()  {
//
//        allNotificationsPage.sidebarClient.home();
//        homeClientPage.placeOrder();
//        typeOrdersPage.Maintenance();
//        infoMaintenancePage.nextButton();
//        selectObjectMaintenancePage.firstObject();
//        selectDateMaintenanceClientPage.pickNowDateAM();
//        selectDateMaintenanceClientPage.submitOrder();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.sidebarClient.home();
//        homeClientPage.isOpened();
//        homeClientPage.actionBlockClient.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.popUpClose();
//        allNotificationsPage.sidebarClient.home();
//        homeClientPage.isOpened();
//        homeClientPage.lastOrder.open();
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.getOrderNumber();
//        System.out.println("Order number is " + OrderCardClientPage.getOrderNumber());
//    }
//
//    @DisplayName("ClientReviewFirstService")
//    //    @Disabled
//    @Test
//    void ClientReviewFirstService() {
//        homeClientPage.actionBlockClient.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.readAll();
//        allNotificationsPage.sidebarClient.home();
//        homeClientPage.lastOrder.open();
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.reviewFirstService();
//        selectInsurancePage.isOpened();
//        selectInsurancePage.header.back();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.isOpened();
//    }
//
//
//    public String
//            smsCode = "123456";
//    @DisplayName("ClientAcceptFirstService")
//    //    @Disabled
//    @Test
//    void ClientAcceptFirstService()  {
//        homeClientPage.actionBlockClient.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.readAll();
//        allNotificationsPage.sidebarClient.home();
//        homeClientPage.lastOrder.open();
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.reviewFirstService();
//        selectInsurancePage.isOpened();
//        selectInsurancePage.next();
//        checkDocumentsClientPage.isOpened();
//        checkDocumentsClientPage.makeContract();
//        selectPaymentClientPage.isOpened();
//        selectPaymentClientPage.paySPB();
//        signSMSClientPage.isOpened();
//        signSMSClientPage.inputSMSCode(smsCode);
//    }

}
