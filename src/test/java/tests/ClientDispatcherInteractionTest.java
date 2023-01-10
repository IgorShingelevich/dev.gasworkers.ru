package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LandingPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.profilePages.AllNotificationsPage;
import pages.profilePages.ObjectCardClientPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;
import pages.profilePages.dispatcherPages.AllNotificationsDispatcherPage;
import pages.profilePages.dispatcherPages.BaseDispatcherPage;
import pages.profilePages.dispatcherPages.HomeDispatcherPage;
import pages.profilePages.dispatcherPages.OrderCardDispatcherPage;

import static com.codeborne.selenide.Selenide.open;

public class ClientDispatcherInteractionTest extends TestBase {


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
    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    AllNotificationsDispatcherPage allNotificationsDispatcherPage = new AllNotificationsDispatcherPage();
    orderCardClientPage orderCardClientPage = new orderCardClientPage();
    ObjectCardClientPage objectCardClientPage = new ObjectCardClientPage();
    AllInvoicesClientPage allInvoicesClientPage = new AllInvoicesClientPage();
    LandingPage landingPage = new LandingPage();
    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
    FocusHeaderComponent focusHeader = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeader = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new  SelectServicePage();
    SelectInsuranceClientPage selectInsurancePage = new SelectInsuranceClientPage();
    CheckDocumentsClientPage checkDocumentsClientPage = new CheckDocumentsClientPage();
    SelectPaymentClientPage selectPaymentClientPage = new SelectPaymentClientPage();
    SignSMSClientPage signSMSClientPage = new SignSMSClientPage();

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
        emailDispatcher = "test_gw_dispatcher_sssr1@rambler.ru",
        passwordDispatcher = "123456";

    String
        emailMaster = "test_gas_master1@rambler.ru",
        passwordMaster = "123456";

    String
        smsCode = "123456";




    @Test
    public void testInteractionTwoRoles() throws InterruptedException {

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

       /* ChromeDriver driver3 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver3);
        open("/login");
        loginPage.login(emailMaster, passwordMaster);*/

        ChromeDriver driverClient = driver1;
        ChromeDriver driverDispatcher = driver2;
//        ChromeDriver driverMaster = driver3;


        WebDriverRunner.setWebDriver(driverClient);
        homeClientPage.placeOrder();
        homeClientPage.popUpClose();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
//        selectServicePage.isOpened();
//        selectServicePage.toOrder();
//        orderCardClientPage.sidebar.home();
//        homeClientPage.isOpened();
//        homeClientPage.actionBlock.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.readAll();
//        allNotificationsPage.sidebar.home();

        WebDriverRunner.setWebDriver(driverDispatcher);


//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.switchToListView();
//        homeDispatcherPage.actionBlock.allNotifications();
//        allNotificationsDispatcherPage.isOpened();
//        allNotificationsDispatcherPage.readAllNotifications();
        //fall
//        allNotificationsDispatcherPage.openFirstNotification();
        homeDispatcherPage.switchToListView();
        homeDispatcherPage.popUpClose();
        homeDispatcherPage.actionBlock.allNotifications();
        allNotificationsDispatcherPage.isOpened();
//        allNotificationsDispatcherPage.readAllNotifications();
        allNotificationsDispatcherPage.sidebar.home();
        homeDispatcherPage.isOpened();
        homeDispatcherPage.switchToListView();

        homeDispatcherPage.openFirstOrderByTitleIndex();
//        orderCardPage.isOpened();
        homeDispatcherPage.popUpClose();
        orderCardPage.acceptOrder();
//        orderCardPage.sidebar.home();

        WebDriverRunner.setWebDriver(driverClient);
//        homeClientPage.actionBlock.allNotifications();
//        allNotificationsPage.isOpened();
//        allNotificationsPage.readAll();
//        allNotificationsPage.sidebar.home();
//        homeClientPage.lastOrder.open();
//        orderCardClientPage.isOpened();
//        orderCardClientPage.toMap();
        selectServicePage.isOpened();
        selectServicePage.waitForResponses();

        selectServicePage.reviewFirstService();
//        selectInsurancePage.isOpened();
        selectInsurancePage.next();
//        checkDocumentsClientPage.isOpened();
        checkDocumentsClientPage.makeContract();
//        selectPaymentClientPage.isOpened();
        selectPaymentClientPage.paySPB();
//        signSMSClientPage.isOpened();
        signSMSClientPage.inputSMSCode(smsCode);





















    }

}
