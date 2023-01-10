package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import pages.BasePage;
import pages.LandingPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.profilePages.AllNotificationsPage;
import pages.profilePages.ObjectCardClientPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;

import static com.codeborne.selenide.Selenide.*;

public class ClientFlowTest extends TestBase {

    SidebarClientComponent sidebar = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();
    HomeClientPage homeClientPage = new HomeClientPage();
    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    ActionsBlockClientComponent actionsBlock = new ActionsBlockClientComponent();
    AllObjectsClientPage allObjectsClientPage = new AllObjectsClientPage();
    AllOrdersClientPage allOrdersClientPage = new AllOrdersClientPage();
    orderCardClientPage orderCardClientPage = new orderCardClientPage();
    ObjectCardClientPage objectCardClientPage = new ObjectCardClientPage();
    AllInvoicesClientPage allInvoicesClientPage = new AllInvoicesClientPage();
    LandingPage landingPage = new LandingPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeader = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new  SelectServicePage();
    CancelMaintenancePage cancelMaintenancePage = new CancelMaintenancePage();
    SelectInsuranceClientPage selectInsurancePage = new SelectInsuranceClientPage();
    CheckDocumentsClientPage checkDocumentsClientPage = new CheckDocumentsClientPage();
    SelectPaymentClientPage selectPaymentClientPage = new SelectPaymentClientPage();
    SignSMSClientPage signSMSClientPage = new SignSMSClientPage();

    @BeforeEach
    void clientLogin() {
        open("/login");
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
    }

    @AfterEach
    void clientLogOut() {
        actionsBlock.logout();
    }

    String emailClient = "shingelevich@gmail.com",
            clientName = "Шингелевич Игорь Сергеевич",
            passwordClient = "123456";



    @DisplayName("CreateNewObject")
    @Test
    void CreateNewObject() {
        homeClientPage.sidebarClient.allObjects();
        allObjectsClientPage.checkObjectsPageTitle();
        allObjectsClientPage.clickCreateNewObject();
        back();
        allObjectsClientPage.sidebarClient.home();
    }

    @DisplayName("ClientPlaceMaintenanceRequestAndCancel")
//    @Disabled
    @Test
    public void ClientPlaceMaintenanceRequestAndCancel() {
        homeClientPage.placeOrder();

        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.cancelOrder();
        cancelMaintenancePage.noButton();
        orderCardClientPage.isOpened();
        orderCardClientPage.popUpClose();
        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.cancelOrder();
        cancelMaintenancePage.yesButton();
        homeClientPage.isOpened();
        homeClientPage.actionBlockClient.allNotifications();
    }

    @DisplayName("openRandomObject")
    @Test
    void openRandomObject() {
        homeClientPage.sidebarClient.allObjects();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebarClient.allObjects();
        allObjectsClientPage.isOpened();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebarClient.allObjects();
        allObjectsClientPage.isOpened();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebarClient.home();
    }

    @Description("ClientPlaceRequest")
    //    @Disabled
    @Test
    void placeMaintenanceFirstObject() throws InterruptedException {

        allNotificationsPage.sidebarClient.home();
        homeClientPage.placeOrder();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.sidebarClient.home();
        homeClientPage.isOpened();
        homeClientPage.actionBlockClient.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebarClient.home();
    }

    @DisplayName("ClientReviewFirstService")
    //    @Disabled
    @Test
    void ClientReviewFirstService() throws InterruptedException {
        homeClientPage.actionBlockClient.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebarClient.home();
        homeClientPage.lastOrder.open();
        orderCardClientPage.isOpened();
        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.reviewFirstService();
        selectInsurancePage.isOpened();
        selectInsurancePage.header.back();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.isOpened();
    }


    public String
            smsCode = "123456";
    @DisplayName("ClientAcceptFirstService")
    //    @Disabled
    @Test
    void ClientAcceptFirstService() throws InterruptedException {
        homeClientPage.actionBlockClient.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebarClient.home();
        homeClientPage.lastOrder.open();
        orderCardClientPage.isOpened();
        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.reviewFirstService();
        selectInsurancePage.isOpened();
        selectInsurancePage.next();
        checkDocumentsClientPage.isOpened();
        checkDocumentsClientPage.makeContract();
        selectPaymentClientPage.isOpened();
        selectPaymentClientPage.paySPB();
        signSMSClientPage.isOpened();
        signSMSClientPage.inputSMSCode(smsCode);
        int i=0;





    }









}

































    
    
   
    






