package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.components.SidebarClientComponent;
import pages.profile.client.ProfileClientPage;

import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    // new exemplar of class SidebarClientComponent
    SidebarClientComponent sidebarClientComponent = new SidebarClientComponent();
    // new LoginPage exemplar
    pages.LoginPage loginPage = new pages.LoginPage();

    // new exemplar of class ProfileClientPage
    ProfileClientPage profileClientPage = new ProfileClientPage();

    // new exemplar of  ActionsBlockClientComponent 
    pages.components.header.ActionsBlockClientComponent actionsBlockClientComponent = new pages.components.header.ActionsBlockClientComponent();
    
    

@BeforeEach
    void openLoginPage() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
    }

    @AfterEach
    void logout() {
        actionsBlockClientComponent.logout();
        //set sleep for logout 5 sec
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    String emailClient = "shingelevich@gmail.com";
    String ClientName = "Шингелевич Игорь Сергеевич";
    String passwordClient = "123456";
    @Test


    /*void sidebarClientItems() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
        profileClientPage.verifyLastOrderInfoTitleLocator();
        sidebarClientComponent.visibleMainPage();
        sidebarClientComponent.clickObjectsAndEquipmentLocator();
        sidebarClientComponent.clickOrdersAndInvoicesDropdownLocator();
        sidebarClientComponent.clickOrdersListLocator();
        sidebarClientComponent.clickInvoicesListLocator();
        sidebarClientComponent.clickProfileLocator();
        sidebarClientComponent.click2MainPage();
    }*/

    void ProfileClientLocatorsIntegrity() { // step scenario

        step("Verify last order info title", () -> {
            profileClientPage.verifyLastOrderInfoTitleLocator();
        });
        step("Click main page", () -> {
            sidebarClientComponent.visibilityMainPage();
        });
        step("Click objects and equipment", () -> {
            sidebarClientComponent.visibilityObjectsAndEquipmentLocator();
        });
        step("Click orders and invoices dropdown", () -> {
            sidebarClientComponent.clickOrdersAndInvoicesDropdownLocator();
        });
        step("Click orders list", () -> {
            sidebarClientComponent.visibilityOrdersListLocator();
        });
        step("Click invoices list", () -> {
            sidebarClientComponent.visibilityInvoicesListLocator();
        });
        step("Click profile", () -> {
            sidebarClientComponent.visibilityProfileLocator();
        });

    }
    
    //   ActionsBlockClientComponent
    @Test
    void ActionsBlockClientComponent() {
        actionsBlockClientComponent.visibilityActionsBlock();
        actionsBlockClientComponent.visibilityLinkNotificationsABLocator();
        actionsBlockClientComponent.visibilityLinkMessagesABLocator();
        actionsBlockClientComponent.visibilityDropdownProfileABLocator();
        actionsBlockClientComponent.clickDropdownProfileABLocator();
        actionsBlockClientComponent.visibilityProfileNameABLocator(ClientName);
        actionsBlockClientComponent.visibilityLinkProfileEditABLocator();
        actionsBlockClientComponent.visibilityLinkReviewABLocator();
        actionsBlockClientComponent.visibilityLinkLogoutABLocator();
        actionsBlockClientComponent.clickDropdownProfileABLocator();

    }
    
    
   
    





}
