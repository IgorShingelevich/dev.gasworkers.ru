//package tests.locatorsTest;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import pages.common.LandingPage;
//import pages.client.HomeClientPage;
//import pages.common.LoginPage;
//import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
//import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
//import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
//import pages.profilePages.AllNotificationsPage;
//import pages.profilePages.clientPages.*;
//import pages.client.InfoTypeOrderClientPage;
//import tests.TestBase;
//
//import java.io.FileNotFoundException;
//
//import static com.codeborne.selenide.Selenide.back;
//import static com.codeborne.selenide.Selenide.open;
//import static io.qameta.allure.Allure.step;
//
//public class ClientLocatorsTest extends TestBase {
//
//    LandingPage landingPage = new LandingPage();
//    HomeClientPage homePage = null;
//    AllOrdersClientPage allOrdersPage = new AllOrdersClientPage();
//    AllInvoicesClientPage allInvoicesPage = new AllInvoicesClientPage();
//    SidebarClientComponent sidebar = new SidebarClientComponent();
//    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
//    ActionsBlockClientComponent actionsBlock = new ActionsBlockClientComponent();
//    LoginPage loginPage = new LoginPage();
//    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
//    InfoTypeOrderClientPage infoMaintenancePage = new InfoTypeOrderClientPage();
//    InfoRepairPage infoRepairPage = new InfoRepairPage();
//    InfoVideoPage infoVideoPage = new InfoVideoPage();
//    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
//    SelectObjectRepairPage selectObjectRepairPage = new SelectObjectRepairPage();
//    SelectDateMaintenanceClientPage selectDateMaintenancePage = new SelectDateMaintenanceClientPage();
//    SelectDateRepairPage selectDateRepairPage = new SelectDateRepairPage();
//    OrderCardClientPage OrderCardClientPage = new OrderCardClientPage();
//
//
//
//    String emailClient = "shingelevich@gmail.com",
//            clientName = "Шингелевич Игорь Сергеевич",
//            passwordClient = "123456";
//
//
//
//    @BeforeEach
//    void clientLogin() {
//        open("/login");
//        loginPage.open();
//        loginPage.login(emailClient, passwordClient);
//    }
//
//
//    @AfterEach
//    void clientLogOut() {
//        actionsBlock.logout();
//    }
//
//    @DisplayName("notificationsLocatorIntegrityTest")
////    @Disabled
//    @Test
//    void notificationsPageLocatorIntegrityTest() {
//        homePage.actionBlockClient.notificationsButton();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.verifyLocators();
//        allNotificationsPage.sidebarClient.home();
//    }
//
//    @DisplayName("landingPageLocatorsIntegrityTest")
//    @Test
//    void landingPageLocatorsIntegrityTest () {
//        landingPage.open();
//        landingPage.isOpened();
//        landingPage.clickUserMaintenanceButton();
//        infoMaintenancePage.isOpened();
//        infoMaintenancePage.nextButton();
//        selectObjectMaintenancePage.isOpened();
//        selectObjectMaintenancePage.firstObject();
//        selectDateMaintenancePage.isOpened();
//        selectDateMaintenancePage.header.logo();
//        landingPage.isOpened();
//        landingPage.clickUserRepairButton();
//        infoRepairPage.isOpened();
//        infoRepairPage.nextButton();
//        selectObjectRepairPage.isOpened();
//        selectObjectRepairPage.pick1thObject1thEquipment();
//        selectDateRepairPage.isOpened();
//        selectDateRepairPage.header.logo();
//        landingPage.clickUserProfile();
//    }
//
//    @DisplayName("ordersAndInvoicesLocatorsIntegrityTest")
//    @Test
//    void ordersAndInvoicesLocatorsIntegrityTest() {
//        allOrdersPage.openPage();
//        allOrdersPage.sidebar.home();
//        homePage.sidebarClient.clickOrdersAndInvoicesDropdown();
//        homePage.sidebarClient.allOrders();
//        allOrdersPage.dropdownAction(1);
//        allOrdersPage.openAction(1);
//        back();
//        allOrdersPage.breadcrumbs.home();
//        homePage.sidebarClient.clickOrdersAndInvoicesDropdown();
//        homePage.sidebarClient.invoices();
//        allInvoicesPage.sidebarClient.home();
//    }
//
//    @DisplayName("openCompletedOrderCard")
//    @Test
//    void openCompletedOrderCard() throws FileNotFoundException {
//        homePage.sidebarClient.clickOrdersAndInvoicesDropdown();
//        homePage.sidebarClient.allOrders();
//        allOrdersPage.orderByNumber(2178);
//        OrderCardClientPage.isOpened();
//        OrderCardClientPage.isCompleteState();
//        OrderCardClientPage.docsAgreementDownload();
//        OrderCardClientPage.sidebarClient.home();
//    }
//
//    @DisplayName("sidebarAndActionBlockClientLocatorsIntegrityTest")
//    @Test
//    void sidebarAndActionBlockClientLocatorsIntegrityTest() {
//        step("homeClientPage.sidebar.verifyLocators", () -> {
//            homePage.sidebarClient.verifyLocators();
//        });
//        step("homeClientPage.actionBlock.verifyLocators", () -> {
//            homePage.actionBlockClient.verifyLocators();
//        });
//    }
//
//
//
//
//
//}
