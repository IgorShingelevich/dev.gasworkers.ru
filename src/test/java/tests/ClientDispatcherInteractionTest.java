package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LandingPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.profilePages.ObjectCardClientPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;
import pages.profilePages.dispatcherPages.HomeDispatcherPage;
import pages.profilePages.dispatcherPages.OrderCardDispatcherPage;

import static com.codeborne.selenide.Selenide.open;

public class ClientDispatcherInteractionTest {


    //clientPages
//    SidebarClientComponent sidebar = new SidebarClientComponent();
//    ActionsBlockClientComponent actionsBlock = new ActionsBlockClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();
    HomeClientPage homeClientPage = new HomeClientPage();
    //    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    AllObjectsClientPage allObjectsClientPage = new AllObjectsClientPage();
    AllOrdersClientPage allOrdersClientPage = new AllOrdersClientPage();
    LastOrderCardClientPage lastOrderCardClientPage = new LastOrderCardClientPage();
    ObjectCardClientPage objectCardClientPage = new ObjectCardClientPage();
    AllInvoicesClientPage allInvoicesClientPage = new AllInvoicesClientPage();
    LandingPage landingPage = new LandingPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeader = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new  SelectServicePage();
    CancelMaintenancePage cancelMaintenancePage = new CancelMaintenancePage();

    //dispatcherPages
//    ActionsBlockDispatcherComponent actionBlock = new ActionsBlockDispatcherComponent();
//    SidebarDispatcherComponent sidebar = new SidebarDispatcherComponent();
    HomeDispatcherPage homeDispatcherPage = new HomeDispatcherPage();
//    AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage();
//    AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage();
//    ProfileDispatcherPage profilePage = new ProfileDispatcherPage();
    OrderCardDispatcherPage orderCardPage = new OrderCardDispatcherPage();








    String
            emailClient = "shingelevich@gmail.com",
            passwordClient = "123456";

    String
            emailDispatcher = "test_gas_disp9@rambler.ru",
            passwordDispatcher = "123456";

    String
            emailMaster = "test_gas_master1@rambler.ru",
            passwordMaster = "123456";

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://dev.gasworkers.ru";
    }

    @Test
    public void testLoginTwoRoles() {

        ChromeDriver driver1 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver1);
//        Configuration.browserSize = "1920x1080";
//        Configuration.browserPosition = "0x0";
        open("/login");
        loginPage.login(emailClient, passwordClient);

        ChromeDriver driver2 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver2);
//        Configuration.browserSize = "1920x1080";
//        Configuration.browserPosition = "1920x0";
        open("/login");
        loginPage.login(emailDispatcher, passwordDispatcher);

        ChromeDriver driver3 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver3);
        open("/login");
        loginPage.login(emailMaster, passwordMaster);

        //set variables to all 3 drivers to  switch between them any time
        ChromeDriver driverClient = driver1;
        ChromeDriver driverDispatcher = driver2;
        ChromeDriver driverMaster = driver3;


         //switch to client driver from any other active driver
        WebDriverRunner.setWebDriver(driverClient);
        homeClientPage.placeOrder();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
        selectServicePage.isOpened();
//        homeClientPage.actionBlock.logout();
        //switch to dispatcher driver from any other active driver
        WebDriverRunner.setWebDriver(driverDispatcher);
        //switch to Client driver and put the browser on top of Master and Dispatcher opened browsers




















    }

}
