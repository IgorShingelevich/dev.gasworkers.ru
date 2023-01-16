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


    @BeforeEach
    void clientLogin() {
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().login(emailClient, passwordClient);
    }

    @Test
    @DisplayName("Check fio information")
    void checkFioInformation() {
        step("Check fio information", () -> {
            clientPages.getHomePage().checkFio(clientName);
        });

    }


    @DisplayName("ClientPlaceMaintenanceRequestAndCancel")
//    @Disabled
    @Test
    public void ClientPlaceMaintenanceRequestAndCancel() {
        step("Client steps - place Order", () -> {
            clientPages.getHomePage()
                    .checkFinishLoading()
                    .clickPlaceOrderButton();

            clientPages.getTypeOrdersPage()
                    .selectOrderType(ClientOrderType.MAINTENANCE);
            clientPages.getInfoTypeOrderPage()
//                    .checkTitle("Заказ на ТО")
//                    .checkStepSequence("Шаг 1 из 3")
                    .clickNextButton();

            clientPages.getSelectObjectMaintenancePage().selectObjectByIndex(0);

            clientPages.getSelectDateMaintenancePage().pickNowDateAM();
            clientPages.getSelectDateMaintenancePage().submitOrder();
            clientPages.getSelectServicePage().checkFinishLoading();
            clientPages.getSelectServicePage().toOrder();
        });
//        homeClientPage.placeOrder();
//        typeOrdersPage.Maintenance();
//        infoMaintenancePage.nextButton();
//        selectObjectMaintenancePage.firstObject();
//        selectDateMaintenanceClientPage.pickNowDateAM();
//        selectDateMaintenanceClientPage.submitOrder();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.cancelOrder();
//        cancelMaintenancePage.noButton();
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.popUpClose();
//        OrderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        OrderCardClientPage.cancelOrder();
//        cancelMaintenancePage.yesButton();
//        homeClientPage.isOpened();
//        homeClientPage.actionBlockClient.allNotifications();
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
