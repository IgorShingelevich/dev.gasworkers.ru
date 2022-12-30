package tests;

import org.junit.jupiter.api.*;
import pages.LandingPage;
import pages.components.SidebarClientComponent;
import pages.profile.client.ClientProfilePage;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();

    ClientProfilePage clientProfilePage = new ClientProfilePage();

    pages.components.header.ActionsBlockClientComponent actionsBlockClient = new pages.components.header.ActionsBlockClientComponent();

//    EquipmentPage equipmentPage = new EquipmentPage();
    pages.profile.client.EquipmentPage equipmentPage = new pages.profile.client.EquipmentPage();

    LandingPage landingPage = new LandingPage();


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
            clientProfilePage.isVisibleLastOrderHeadline();
        });
    }

    @DisplayName("equipmentClientPageLocatorsIntegrityTest")
    @Test
    void equipmentClientPageLocatorsIntegrity() {
        clientProfilePage.isVisibleLastOrderHeadline();
        equipmentPage.open();
        landingPage.open();
        back();
        equipmentPage.checkEquipmentPageTitle();
        equipmentPage.clickCreateNewObject();
        back();


    }

    @DisplayName("objectClientPageLocatorsIntegrityTest")
    @Test
    void objectClientPageLocatorsIntegrity() {
        clientProfilePage.goto1thObjectActionButtonLocator();

    }



    @DisplayName("landingPageLocatorsIntegrityTest")
    @Test
    void landingPageLocatorsIntegrityTest () {
        step("openLandingPage", () -> {
            landingPage.open();
        });



        step("visibilityPrimaryHeaderLocator", () -> {
            landingPage.visibilityPrimaryHeaderLocator();
        });
        step("clickRepairButton", () -> {
            landingPage.clickRepairButton();
            back();
        });
        step("clickMaintenanceButton", () -> {
            landingPage.clickMaintenanceButton();
            back();
        });
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
















    
    
   
    






