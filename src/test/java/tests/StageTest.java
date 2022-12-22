package tests;

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


    String emailClient = "shingelevich@gmail.com";
    String passwordClient = "123456";
    @Test


    /*void sidebarClientItems() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
        profileClientPage.verifyLastOrderInfoTitleLocator();
        sidebarClientComponent.clickMainPage();
        sidebarClientComponent.clickObjectsAndEquipmentLocator();
        sidebarClientComponent.clickOrdersAndInvoicesDropdownLocator();
        sidebarClientComponent.clickOrdersListLocator();
        sidebarClientComponent.clickInvoicesListLocator();
        sidebarClientComponent.clickProfileLocator();
        sidebarClientComponent.click2MainPage();
    }*/

    void ProfileClientLocatorsIntegrity() { // step scenario
        step("Open login page", () -> {
            loginPage.open();
        });
        step("Login as client", () -> {
            loginPage.login(emailClient, passwordClient);
        });
        step("Verify last order info title", () -> {
            profileClientPage.verifyLastOrderInfoTitleLocator();
        });
        step("Click main page", () -> {
            sidebarClientComponent.clickMainPage();
        });
        step("Click objects and equipment", () -> {
            sidebarClientComponent.clickObjectsAndEquipmentLocator();
        });
        step("Click orders and invoices dropdown", () -> {
            sidebarClientComponent.clickOrdersAndInvoicesDropdownLocator();
        });
        step("Click orders list", () -> {
            sidebarClientComponent.clickOrdersListLocator();
        });
        step("Click invoices list", () -> {
            sidebarClientComponent.clickInvoicesListLocator();
        });
        step("Click profile", () -> {
            sidebarClientComponent.clickProfileLocator();
        });
        step("Click main page", () -> {
            sidebarClientComponent.click2MainPage();
        });
    }





}
