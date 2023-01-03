package tests;

import org.junit.jupiter.api.*;
import pages.LandingPage;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.infoServicesPages.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPages.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPages.InfoVideoPage;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrders typeOrders = new TypeOrders();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();

    ProfileClientPage profileClientPage = new ProfileClientPage();


    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();

    EquipmentClientPage equipmentClientPage = new EquipmentClientPage();
    OrdersClientPage ordersClientPage = new OrdersClientPage();
    InvoicesClientPage invoicesClientPage = new InvoicesClientPage();

    LandingPage landingPage = new LandingPage();

    SelectMaintenanceObjectPage selectMaintenanceObjectPage = new SelectMaintenanceObjectPage();
    SelectDateClientPage selectDateClientPage = new SelectDateClientPage();
    FocusHeaderComponent focusHeaderComponent = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeaderComponent = new ProfileHeaderComponent();


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

    void sidebarClientLocatorsIntegrity() {


        step("sidebarClient.isVisible", () -> {
            sidebarClient.isVisibleHomeButton();
        });
        step("sidebarClient.isVisibleObjectsAndEquipment", () -> {
            sidebarClient.isVisibleObjectsAndEquipment();
        });
        step("sidebarClient.clickOrdersAndInvoicesDropdown", () -> {
            sidebarClient.clickOrdersAndInvoicesDropdown();
        });
        step("sidebarClient.isVisibleOrdersList", () -> {
            sidebarClient.isVisibleOrdersList();
        });
        step(" sidebarClient.isVisibleInvoicesList", () -> {
            sidebarClient.isVisibleInvoicesList();
        });
        step("sidebarClient.isVisibleProfile", () -> {
            sidebarClient.isVisibleProfile();
        });
        step(" sidebarClient.isVisibleSupportServiceHeadline", () -> {
            sidebarClient.isVisibleSupportServiceHeadline();
        });
        step("sidebarClient.isVisibleSupportServicePhone", () -> {
            sidebarClient.isVisibleSupportServicePhone();
        });
        step(" sidebarClient.isVisibleSupportServiceEmail", () -> {
            sidebarClient.isVisibleSupportServiceEmail();
        });


    }


    @DisplayName("actionsBlockClientComponentLocatorsIntegrityTest")
//    @Disabled
    @Test
     void actionsBlockClientComponentLocatorsIntegrity() {
        step("actionsBlockClient.isVisible", () -> {
            actionsBlockClient.isVisible();
        });
        step("actionsBlockClient.isVisibleNotificationsButton", () -> {
            actionsBlockClient.isVisibleNotificationsButton();
        });
        step("actionsBlockClient.isVisibleMessagesButton", () -> {
            actionsBlockClient.isVisibleMessagesButton();
        });
        step("actionsBlockClient.isVisibleDropdown", () -> {
            actionsBlockClient.isVisibleDropdown();
        });
       /* step("actionsBlockClient.clickDropdown2", () -> {
            actionsBlockClient.clickDropdown2();
        });*/
        step("actionsBlockClient.isVisibleProfileName", () -> {
            actionsBlockClient.isVisibleProfileName();
        });
        step("actionsBlockClient.isVisibleProfileButton", () -> {
            actionsBlockClient.isVisibleProfileButton();
        });
        step("actionsBlockClient.isVisibleReviewButton", () -> {
            actionsBlockClient.isVisibleReviewButton();
        });

        step("actionsBlockClient.isVisibleLogoutButton", () -> {
            actionsBlockClient.isVisibleLogoutButton();
        });



    }

    @DisplayName("clientProfilePageLocatorsIntegrityTest")
    @Test
    void clientProfilePageLocatorsIntegrityTest(){
        step("verifyProfileClientName", () -> {
            profileClientPage.verifyProfileClientName(clientName);
        });
        step("isVisibleLastOrderHeadline", () -> {
            profileClientPage.isOpened();
        });
    }

    @DisplayName("equipmentClientPageLocatorsIntegrityTest")
    @Test
    void equipmentClientPageLocatorsIntegrity() {
        profileClientPage.isOpened();
        equipmentClientPage.open();
        landingPage.open();
        back();
        equipmentClientPage.checkEquipmentPageTitle();
        equipmentClientPage.clickCreateNewObject();
        back();


    }

    @DisplayName("objectClientPageLocatorsIntegrityTest")
    @Test
    void objectClientPageLocatorsIntegrity() {
        profileClientPage.isOpened();
//        profileClientPage.goto1thObjectActionButtonLocator();
    }


    @DisplayName("typeOrdersLocatorsIntegrityTest")
    @Test
    void lastOrderClientPageLocatorsIntegrity() {
        profileClientPage.isOpened();
        ordersClientPage.openPage();
        ordersClientPage.sidebar.clickMain();
        profileClientPage.sidebar.clickOrdersAndInvoicesDropdown();
        profileClientPage.sidebar.clickOrdersList();
        ordersClientPage.clickOrderCardActionButton(1);
        ordersClientPage.openOrder(1);
        back();
        ordersClientPage.breadcrumbMain();
        profileClientPage.sidebar.clickOrdersAndInvoicesDropdown();
        profileClientPage.sidebar.clickInvoicesList();
        invoicesClientPage.sidebar.clickMain();

    }



    @DisplayName("landingPageLocatorsIntegrityTest")
    @Test
    void landingPageLocatorsIntegrityTest () {
            landingPage.open();
            landingPage.isOpened();
            landingPage.clickUserMaintenanceButton();
            infoMaintenancePage.isOpened();
            infoMaintenancePage.clickMaintenanceButton();
            selectMaintenanceObjectPage.isOpened();
            selectMaintenanceObjectPage.clickObject1thButton();
            selectDateClientPage.isOpened();
            focusHeaderComponent.clickLogo();
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













}


/**questions
 * flex after html elements. How flex affects the Locators elements
 * list of devtools shortcuts
 *OrdersClientPage -  look at example of openPage and isOpened methods.
 * isOpened method should contain all the checks of static information available on the page
 //$(".breadcrumb li:nth-child(2)"), - learn how to use :nth-child
 *
 * */













    
    
   
    






