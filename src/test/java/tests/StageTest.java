package tests;

import org.junit.jupiter.api.*;
import pages.LandingPage;
import pages.components.SidebarClientComponent;
import pages.components.header.FocusHeaderComponent;
import pages.components.header.ProfileHeaderComponent;
import pages.components.header.actionblock.ActionsBlockClientComponent;
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

    ClientProfilePage clientProfilePage = new ClientProfilePage();

    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();

//    EquipmentClientPage equipmentClientPage = new EquipmentClientPage();
    EquipmentClientPage equipmentClientPage = new EquipmentClientPage();

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
            clientProfilePage.verifyProfileClientName(clientName);
        });
        step("isVisibleLastOrderHeadline", () -> {
            clientProfilePage.isOpened();
        });
    }

    @DisplayName("equipmentClientPageLocatorsIntegrityTest")
    @Test
    void equipmentClientPageLocatorsIntegrity() {
        clientProfilePage.isOpened();
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
        clientProfilePage.isOpened();
        clientProfilePage.goto1thObjectActionButtonLocator();

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
    void openClientTwoTabs() {

        executeJavaScript("window.open('https://dev.gasworkers.ru/','_blank');");
        switchTo().window(1);
        open("https://dev.gasworkers.ru/profile/edit");
        switchTo().window(0);

    }











}


/**questions
 * flex after html elements. How flex affects the Locators elements
 *
 * */













    
    
   
    






