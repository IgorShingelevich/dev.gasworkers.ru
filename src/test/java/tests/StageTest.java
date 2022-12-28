package tests;

import org.junit.jupiter.api.*;
import pages.components.SidebarClientComponent;
import pages.profile.client.ClientProfilePage;
import pages.profile.client.EquipmentPage;

import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    // new exemplar of class SidebarClientComponent
    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    // new LoginPage exemplar
    pages.LoginPage loginPage = new pages.LoginPage();

    // new exemplar of class ClientProfilePage
    ClientProfilePage clientProfilePage = new ClientProfilePage();

    // new exemplar of  ActionsBlockClientComponent 
    pages.components.header.ActionsBlockClientComponent actionsBlockClient = new pages.components.header.ActionsBlockClientComponent();

    // new exemplar of    pages.profile.client.EquipmentPage
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

    void sidebarClientLocatorsIntegrity() { // step scenario

//        step("Verify last order info title", () -> {
            clientProfilePage.isOpened();
//        });
//        step("Click main page", () -> {
            sidebarClient.isVisible();
//        });
//        step("Click objects and equipment", () -> {
            sidebarClient.isVisibleObjectsAndEquipment();
//        });
//        step("Click orders and invoices dropdown", () -> {
            sidebarClient.clickOrdersAndInvoicesDropdown();
//        });
//        step("Click orders list", () -> {
            sidebarClient.isVisibleOrdersList();
//        });
//        step("Click invoices list", () -> {
            sidebarClient.isVisibleInvoicesList();
//        });
//        step("Click profile", () -> {
            sidebarClient.isVisibleProfile();
//        });
//        actionsBlockClient.clickDropdownProfileABLocator();


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

    @DisplayName("equipmentClientPageLocatorsIntegrityTest")
    @Test
    void equipmentClientPageLocatorsIntegrity() {
        clientProfilePage.isOpened();
        equipmentPage.open();
//        actionsBlockClient.clickDropdownProfileABLocator();


    }


}





    
    
   
    






