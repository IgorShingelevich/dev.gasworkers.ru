package tests.locatorsTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LandingPage;
import pages.LoginPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.profilePages.AllNotificationsPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ClientLocatorsTest extends TestBase {

    LandingPage landingPage = new LandingPage();
    HomeClientPage homePage = new HomeClientPage();
    AllOrdersClientPage allOrdersPage = new AllOrdersClientPage();
    AllInvoicesClientPage allInvoicesPage = new AllInvoicesClientPage();
    SidebarClientComponent sidebar = new SidebarClientComponent();
    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
    ActionsBlockClientComponent actionsBlock = new ActionsBlockClientComponent();
    LoginPage loginPage = new LoginPage();
    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectObjectRepairPage selectObjectRepairPage = new SelectObjectRepairPage();
    SelectDateMaintenanceClientPage selectDateMaintenancePage = new SelectDateMaintenanceClientPage();
    SelectDateRepairPage selectDateRepairPage = new SelectDateRepairPage();



    String emailClient = "shingelevich@gmail.com",
            clientName = "Шингелевич Игорь Сергеевич",
            passwordClient = "123456";


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

    @DisplayName("notificationsLocatorIntegrityTest")
//    @Disabled
    @Test
    void notificationsPageLocatorIntegrityTest() {
        homePage.actionBlock.notificationsButton();
        allNotificationsPage.isOpened();
        allNotificationsPage.verifyLocators();
        allNotificationsPage.sidebar.home();
    }

    @DisplayName("landingPageLocatorsIntegrityTest")
    @Test
    void landingPageLocatorsIntegrityTest () {
        landingPage.open();
        landingPage.isOpened();
        landingPage.clickUserMaintenanceButton();
        infoMaintenancePage.isOpened();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.isOpened();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenancePage.isOpened();
        selectDateMaintenancePage.header.logo();
        landingPage.isOpened();
        landingPage.clickUserRepairButton();
        infoRepairPage.isOpened();
        infoRepairPage.nextButton();
        selectObjectRepairPage.isOpened();
        selectObjectRepairPage.pick1thObject1thEquipment();
        selectDateRepairPage.isOpened();
        selectDateRepairPage.header.logo();
        landingPage.clickUserProfile();
    }

    @DisplayName("ordersAndInvoicesLocatorsIntegrityTest")
    @Test
    void ordersAndInvoicesLocatorsIntegrityTest() {
        allOrdersPage.openPage();
        allOrdersPage.sidebar.home();
        homePage.sidebar.clickOrdersAndInvoicesDropdown();
        homePage.sidebar.orders();
        allOrdersPage.clickOrderCardActionButton(1);
        allOrdersPage.openOrder(1);
        back();
        allOrdersPage.breadcrumbs.home();
        homePage.sidebar.clickOrdersAndInvoicesDropdown();
        homePage.sidebar.invoices();
        allInvoicesPage.sidebar.home();
    }

    @DisplayName("sidebarAndActionBlockClientLocatorsIntegrityTest")
    @Test
    void sidebarAndActionBlockClientLocatorsIntegrityTest() {
        step("homeClientPage.sidebar.verifyLocators", () -> {
            homePage.sidebar.verifyLocators();
        });
        step("homeClientPage.actionBlock.verifyLocators", () -> {
            homePage.actionBlock.verifyLocators();
        });
    }





}
