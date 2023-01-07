package tests;

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

public class ClientFlowTests extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();
    HomeClientPage homeClientPage = new HomeClientPage();
    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();
    AllObjectsClientPage allObjectsClientPage = new AllObjectsClientPage();
    AllOrdersClientPage allOrdersClientPage = new AllOrdersClientPage();
    OrderCardClientPage orderCardClientPage = new OrderCardClientPage();
    ObjectCardClientPage objectCardClientPage = new ObjectCardClientPage();
    AllInvoicesClientPage allInvoicesClientPage = new AllInvoicesClientPage();
    LandingPage landingPage = new LandingPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
    FocusHeaderComponent focusHeaderComponent = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeaderComponent = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new  SelectServicePage();
    CancelMaintenancePage cancelMaintenancePage = new CancelMaintenancePage();

    @BeforeEach
    void clientLogin() {
        open("/login");
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
    }

    @AfterEach
    void clientLogOut() {
        actionsBlockClient.logout();
    }

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
    void ClientPlaceMaintenanceRequestAndCancel() {
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
        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderCardClientPage.cancelOrder();
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

















}
















    
    
   
    






