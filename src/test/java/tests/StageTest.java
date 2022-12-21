package tests;

import org.junit.jupiter.api.Test;
import pages.components.sidebarClient;

public class StageTest extends TestBase {

    // new exemplar of class sidebarClient
    pages.components.sidebarClient sidebarClient = new pages.components.sidebarClient();
    // new LoginPage exemplar
    pages.LoginPage loginPage = new pages.LoginPage();


    String emailClient = "shingelevich@gmail.com";
    String passwordClient = "123456";
    @Test


    void stepTest() {
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
        sidebarClient.clickMainPage();
        sidebarClient.clickObjectsAndEquipmentLocator();
        sidebarClient.clickOrdersAndInvoicesDropdownLocator();
        sidebarClient.clickOrdersListLocator();
        sidebarClient.clickInvoicesListLocator();
        sidebarClient.clickProfileLocator();
        sidebarClient.click2MainPage();
    }





}
