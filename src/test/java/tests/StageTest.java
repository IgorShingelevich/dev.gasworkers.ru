package tests;

import org.junit.jupiter.api.Test;
import pages.components.SidebarClient;

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


    void sidebarClientItems() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
//        profileClient.verifyLastOrderInfoTitleLocator();
        sidebarClient.clickMainPage();
        sidebarClient.clickObjectsAndEquipmentLocator();
        sidebarClient.clickOrdersAndInvoicesDropdownLocator();
        sidebarClient.clickOrdersListLocator();
        sidebarClient.clickInvoicesListLocator();
        sidebarClient.clickProfileLocator();
        sidebarClient.click2MainPage();
    }





}
