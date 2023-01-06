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
import pages.profilePages.NotificationsPage;
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
    HomeClientPage homeClientPage = new HomeClientPage();
    OrdersClientPage ordersClientPage = new OrdersClientPage();
    InvoicesClientPage invoicesClientPage = new InvoicesClientPage();
    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();
    LoginPage loginPage = new LoginPage();
    NotificationsPage notificationsPage = new NotificationsPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectObjectRepairObjectPage selectObjectRepairObjectPage = new SelectObjectRepairObjectPage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
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
        actionsBlockClient.logout();
    }

    @DisplayName("notificationsLocatorIntegrityTest")
//    @Disabled
    @Test
    void notificationsPageLocatorIntegrityTest() {
        homeClientPage.actionBlock.notificationsButton();
        notificationsPage.isOpened();
        notificationsPage.verifyLocators();
        notificationsPage.sidebar.home();
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
        selectDateMaintenanceClientPage.isOpened();
        selectDateMaintenanceClientPage.header.logo();
        landingPage.isOpened();
        landingPage.clickUserRepairButton();
        infoRepairPage.isOpened();
        infoRepairPage.nextButton();
        selectObjectRepairObjectPage.isOpened();
        selectObjectRepairObjectPage.pick1thObject1thEquipment();
        selectDateRepairPage.isOpened();
        selectDateRepairPage.header.logo();
        landingPage.clickUserProfile();
    }

    @DisplayName("ordersAndInvoicesLocatorsIntegrityTest")
    @Test
    void ordersAndInvoicesLocatorsIntegrityTest() {
        ordersClientPage.openPage();
        ordersClientPage.sidebar.home();
        homeClientPage.sidebar.clickOrdersAndInvoicesDropdown();
        homeClientPage.sidebar.orders();
        ordersClientPage.clickOrderCardActionButton(1);
        ordersClientPage.openOrder(1);
        back();
        ordersClientPage.breadcrumbs.home();
        homeClientPage.sidebar.clickOrdersAndInvoicesDropdown();
        homeClientPage.sidebar.invoices();
        invoicesClientPage.sidebar.home();
    }

    @DisplayName("sidebarAndActionBlockClientLocatorsIntegrityTest")
    @Test
    void sidebarAndActionBlockClientLocatorsIntegrityTest() {
        step("homeClientPage.sidebar.verifyLocators", () -> {
            homeClientPage.sidebar.verifyLocators();
        });
        step("homeClientPage.actionBlock.verifyLocators", () -> {
            homeClientPage.actionBlock.verifyLocators();
        });
    }





}
