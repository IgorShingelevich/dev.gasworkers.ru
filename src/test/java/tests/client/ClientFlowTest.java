package tests.client;

import extension.browser.Browser;
import org.junit.jupiter.api.*;
import pages.context.ClientPages;
import tests.TestBase;

import static model.Role.*;

class ClientFlowTest extends TestBase {

    private final String emailClient = "shingelevich@gmail.com";
    private final String clientName = "Шингелевич Игорь Сергеевич";
    private final String passwordClient = "123456";

    @Browser(role = CLIENT)
    ClientPages clientPages;

    @BeforeEach
    void clientLogin() {
        clientPages.getLoginPage().open();
        clientPages.getLoginPage().login(emailClient, passwordClient);
    }

    @Test
    @DisplayName("Check fio information")
    void checkFioInformation() {
        clientPages.getHomePage().checkFio(clientName);
    }


//    @DisplayName("ClientPlaceMaintenanceRequestAndCancel")
////    @Disabled
//    @Test
//    public void ClientPlaceMaintenanceRequestAndCancel() {
//        homeClientPage.placeOrder();
//
//        typeOrdersPage.Maintenance();
//        infoMaintenancePage.nextButton();
//        selectObjectMaintenancePage.firstObject();
//        selectDateMaintenanceClientPage.pickNowDateAM();
//        selectDateMaintenanceClientPage.submitOrder();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        orderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        orderCardClientPage.cancelOrder();
//        cancelMaintenancePage.noButton();
//        orderCardClientPage.isOpened();
//        orderCardClientPage.popUpClose();
//        orderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        orderCardClientPage.cancelOrder();
//        cancelMaintenancePage.yesButton();
//        homeClientPage.isOpened();
//        homeClientPage.actionBlockClient.allNotifications();
//    }
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
//        orderCardClientPage.sidebarClient.home();
//        homeClientPage.isOpened();
//        homeClientPage.actionBlockClient.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.popUpClose();
//        allNotificationsPage.sidebarClient.home();
//        homeClientPage.isOpened();
//        homeClientPage.lastOrder.open();
//        orderCardClientPage.isOpened();
//        orderCardClientPage.getOrderNumber();
//        System.out.println("Order number is " + orderCardClientPage.getOrderNumber());
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
//        orderCardClientPage.isOpened();
//        orderCardClientPage.toMap();
//        selectServicePage.isOpened();
//        selectServicePage.reviewFirstService();
//        selectInsurancePage.isOpened();
//        selectInsurancePage.header.back();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        orderCardClientPage.isOpened();
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
//        orderCardClientPage.isOpened();
//        orderCardClientPage.toMap();
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
