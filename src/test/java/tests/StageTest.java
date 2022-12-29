package tests;

import org.junit.jupiter.api.*;
import pages.components.SidebarClientComponent;
import pages.profile.client.ClientProfilePage;
import pages.profile.client.EquipmentPage;

import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();

    ClientProfilePage clientProfilePage = new ClientProfilePage();

    pages.components.header.ActionsBlockClientComponent actionsBlockClient = new pages.components.header.ActionsBlockClientComponent();

    EquipmentPage equipmentPage = new EquipmentPage();



    @BeforeEach
    void clientLogin() {
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
       /* step("clickDropdownProfileABLocator", () -> {
            actionsBlockClient.clickDropdownProfileABLocator();
        });*/






    }


    @DisplayName("actionsBlockClientComponentLocatorsIntegrityTest")
//    @Disabled
    @Test
     void actionsBlockClientComponentLocatorsIntegrity() {
        actionsBlockClient.visibilityActionsBlock();
        actionsBlockClient.visibilityLinkNotificationsABLocator();
        actionsBlockClient.visibilityLinkMessagesABLocator();
        actionsBlockClient.visibilityDropdownProfileABLocator();
        actionsBlockClient.clickDropdownProfileABLocator();
        actionsBlockClient.visibilityProfileNameABLocator(clientName);
        actionsBlockClient.visibilityLinkProfileEditABLocator();
        actionsBlockClient.visibilityLinkReviewABLocator();
        actionsBlockClient.visibilityLinkLogoutABLocator();
        actionsBlockClient.clickDropdownProfileABLocator();


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
//        clientProfilePage.isVisibleLastOrderHeadline();
        equipmentPage.openPage();
        equipmentPage.createNewObject();






//        actionsBlockClient.clickDropdownProfileABLocator();


    }
    @DisplayName("objectClientPageLocatorsIntegrityTest")
    @Test
    void objectClientPageLocatorsIntegrity() {

        clientProfilePage.goto1thObjectActionButtonLocator();


    }




}





    
    
   
    






