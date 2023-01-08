package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
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
    LastOrderCardClientPage lastOrderCardClientPage = new LastOrderCardClientPage();
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

//    @AfterEach
//    void clientLogOut() {
//        actionsBlock.logout();
//    }

    String emailClient = "shingelevich@gmail.com",
            clientName = "Шингелевич Игорь Сергеевич",
            passwordClient = "123456";



    @DisplayName("CreateNewObject")
    @Test
    void CreateNewObject() {
        homeClientPage.sidebar.allObjects();
        allObjectsClientPage.checkObjectsPageTitle();
        allObjectsClientPage.clickCreateNewObject();
        back();
        allObjectsClientPage.sidebar.home();
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
        lastOrderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        lastOrderCardClientPage.cancelOrder();
        cancelMaintenancePage.noButton();
        lastOrderCardClientPage.isOpened();
        lastOrderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        lastOrderCardClientPage.cancelOrder();
        cancelMaintenancePage.yesButton();
        homeClientPage.isOpened();
        homeClientPage.actionBlock.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebar.home();
        homeClientPage.isOpened();
    }

    @DisplayName("openRandomObject")
    @Test
    void openRandomObject() {
        homeClientPage.sidebar.allObjects();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebar.allObjects();
        allObjectsClientPage.isOpened();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebar.allObjects();
        allObjectsClientPage.isOpened();
        allObjectsClientPage.openRandomObject();
        objectCardClientPage.isOpened();
        objectCardClientPage.sidebar.home();
    }

    @Description("ClientPlaceRepairRequest")
    //    @Disabled
    @Test
    void placeMaintenanceFirstObject() {

        allNotificationsPage.sidebar.home();
        homeClientPage.placeOrder();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        lastOrderCardClientPage.sidebar.home();
        homeClientPage.isOpened();
        homeClientPage.actionBlock.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebar.home();
    }

    @DisplayName("ClientReviewFirstService")
    //    @Disabled
    @Test
    void ClientReviewFirstService(){
        homeClientPage.actionBlock.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebar.home();
        homeClientPage.lastOrder.open();
        lastOrderCardClientPage.isOpened();
        lastOrderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.reviewFirstService();
        selectInsurancePage.isOpened();
        selectInsurancePage.header.back();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        lastOrderCardClientPage.isOpened();
    }


    public String
            smsCode = "123456";
    @DisplayName("ClientAcceptFirstService")
    //    @Disabled
    @Test
    void ClientAcceptFirstService(){
        homeClientPage.actionBlock.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAll();
        allNotificationsPage.sidebar.home();
        homeClientPage.lastOrder.open();
        lastOrderCardClientPage.isOpened();
        lastOrderCardClientPage.toMap();
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

































    
    
   
    






