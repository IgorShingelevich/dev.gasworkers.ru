package tests;

import org.junit.jupiter.api.Test;
import pages.components.SidebarClient;

import static io.qameta.allure.Allure.step;

public class StageTest extends TestBase {

    // new exemplar of class SidebarClient
    SidebarClient sidebarClient = new SidebarClient();
    // new LoginPage exemplar
    pages.LoginPage loginPage = new pages.LoginPage();

    // new exemplar of class ProfileClient
    pages.profile.client.ProfileClient profileClient = new pages.profile.client.ProfileClient();


    String emailClient = "shingelevich@gmail.com";
    String passwordClient = "123456";
    @Test


    /*void sidebarClientItems() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
        profileClient.verifyLastOrderInfoTitleLocator();
        sidebarClient.clickMainPage();
        sidebarClient.clickObjectsAndEquipmentLocator();
        sidebarClient.clickOrdersAndInvoicesDropdownLocator();
        sidebarClient.clickOrdersListLocator();
        sidebarClient.clickInvoicesListLocator();
        sidebarClient.clickProfileLocator();
        sidebarClient.click2MainPage();
    }*/

    void sidebarClientItems() { // step scenario
        step("Open login page", () -> {
            loginPage.open();
        });
        step("Login as client", () -> {
            loginPage.login(emailClient, passwordClient);
        });
        step("Verify last order info title", () -> {
            profileClient.verifyLastOrderInfoTitleLocator();
        });
        step("Click main page", () -> {
            sidebarClient.clickMainPage();
        });
        step("Click objects and equipment", () -> {
            sidebarClient.clickObjectsAndEquipmentLocator();
        });
        step("Click orders and invoices dropdown", () -> {
            sidebarClient.clickOrdersAndInvoicesDropdownLocator();
        });
        step("Click orders list", () -> {
            sidebarClient.clickOrdersListLocator();
        });
        step("Click invoices list", () -> {
            sidebarClient.clickInvoicesListLocator();
        });
        step("Click profile", () -> {
            sidebarClient.clickProfileLocator();
        });
        step("Click main page", () -> {
            sidebarClient.click2MainPage();
        });
    }





}
