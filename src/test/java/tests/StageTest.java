package tests;

import org.junit.jupiter.api.*;
import pages.LandingPage;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();

    HomeClientPage homeClientPage = new HomeClientPage();


    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();

    ObjectsClientPage objectsClientPage = new ObjectsClientPage();
    OrdersClientPage ordersClientPage = new OrdersClientPage();
    OrderClientPage orderClientPage = new OrderClientPage();
    InvoicesClientPage invoicesClientPage = new InvoicesClientPage();

    LandingPage landingPage = new LandingPage();

    SelectMaintenanceObjectPage selectMaintenanceObjectPage = new SelectMaintenanceObjectPage();
    SelectDateClientPage selectDateClientPage = new SelectDateClientPage();
    FocusHeaderComponent focusHeaderComponent = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeaderComponent = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new SelectServicePage();
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

    @DisplayName("sidebarClientLocatorsIntegrityTest")
    @Test
    void homeClientPageLocatorsIntegrity() {
        step("homeClientPage.sidebar.verifyLocators", () -> {
            homeClientPage.sidebar.verifyLocators();
        });
        step("homeClientPage.actionBlock.verifyLocators", () -> {
            homeClientPage.actionBlock.verifyLocators();
        });
    }



    @DisplayName("CreateNewObject")
    @Test
    void CreateNewObject() {
        homeClientPage.sidebar.objects();
        objectsClientPage.checkObjectsPageTitle();
        objectsClientPage.clickCreateNewObject();
        back();
        objectsClientPage.sidebar.home();
    }




    @DisplayName("typeOrdersLocatorsIntegrityTest")
    @Test
    void lastOrderClientPageLocatorsIntegrity() {
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



    @DisplayName("landingPageLocatorsIntegrityTest")
    @Test
    void landingPageLocatorsIntegrityTest () {
            landingPage.open();
            landingPage.isOpened();
            landingPage.clickUserMaintenanceButton();
            infoMaintenancePage.isOpened();
            infoMaintenancePage.nextButton();
            selectMaintenanceObjectPage.isOpened();
            selectMaintenanceObjectPage.pick1thObject();
            selectDateClientPage.isOpened();
            focusHeaderComponent.logo();
            landingPage.isOpened();
            landingPage.clickUserProfile();
    }



     @DisplayName("switchToNewTab")
    @Test
    void switchToNewTab() {

        executeJavaScript("window.open('https://dev.gasworkers.ru/','_blank');");
        switchTo().window(1);
        open("https://dev.gasworkers.ru/profile/edit");
        switchTo().window(0);
    }


    @DisplayName("ClientPlaceMaintenanceRequest and Cancel")
//    @Disabled
    @Test
    void ClientPlaceAndCancelOrder() {
        homeClientPage.placeOrder();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectMaintenanceObjectPage.pick1thObject();
        selectDateClientPage.pickNowDateAM();
        selectDateClientPage.submitOrder();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderClientPage.toMap();
        selectServicePage.toOrder();
        orderClientPage.cancelOrder();
        cancelMaintenancePage.noButton();
        orderClientPage.cancelOrder();
        cancelMaintenancePage.yesButton();
        homeClientPage.isOpened();
    }












}


/**questions
 * flex after html elements. How flex affects the Locators elements
 * list of devtools shortcuts
 *OrdersClientPage -  look at example of openPage and isOpened methods.
 * isOpened method should contain all the checks of static information available on the page
 //$(".breadcrumb li:nth-child(2)"), - learn how to use :nth-child
 put all staticText verifications in one method - .open
 build breadcrumbs -different set on each own page. Within  TheUserRole - each page should contain its own breadcrumbs.
 build header - different set on each own page. Within  the TheUserRole - each page should contain its own header.
 build sidebar - different set on each own page. Within  the TheUserRole - each page should contain its own sidebar.

 *
 * */













    
    
   
    






