package tests;

import org.junit.jupiter.api.*;
import pages.LandingPage;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.profilePages.AllNotificationsPage;
import pages.profilePages.ObjectCardClientPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class StageClientTest extends TestBase {

    SidebarClientComponent sidebar = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();

    HomeClientPage homeClientPage = new HomeClientPage();

    AllNotificationsPage allNotificationsPage = new AllNotificationsPage();
    ActionsBlockClientComponent actionsBlock = new ActionsBlockClientComponent();

    AllObjectsClientPage allObjectsClientPage = new AllObjectsClientPage();
    AllOrdersClientPage allOrdersClientPage = new AllOrdersClientPage();
    LastOrderCardClientPage lastOrderCardClientPage = new LastOrderCardClientPage();
    ObjectCardClientPage objectCardClientPage = new ObjectCardClientPage();
    AllInvoicesClientPage allInvoicesClientPage = new AllInvoicesClientPage();

    LandingPage landingPage = new LandingPage();

    SelectObjectMaintenancePage selectObjectMaintenancePage = new SelectObjectMaintenancePage();
    SelectDateMaintenanceClientPage selectDateMaintenanceClientPage = new SelectDateMaintenanceClientPage();
    FocusHeaderComponent focusHeaderComponent = new FocusHeaderComponent();
    ProfileHeaderComponent profileHeaderComponent = new ProfileHeaderComponent();
    SelectServicePage selectServicePage = new  SelectServicePage();
    CancelMaintenancePage cancelMaintenancePage = new CancelMaintenancePage();


    @BeforeEach
    void clientLogin() {
        open("/login");
        loginPage.open();
        loginPage.login(emailClient, passwordClient);
    }


    @AfterEach
    void clientLogOut() {
        actionsBlock.logout();
    }

    String
        emailClient = "shingelevich@gmail.com",
        clientName = "Шингелевич Игорь Сергеевич",
        passwordClient = "123456";

    String
        emailDispatcher = "test_gas_disp9@rambler.ru",
        passwordDispatcher = "123456";





     @DisplayName("switchToNewTab")
     // @Disabled
    @Test
    void switchToNewTab() {

        executeJavaScript("window.open('https://dev.gasworkers.ru/','_blank');");
        switchTo().window(1);
        open("https://dev.gasworkers.ru/profile/edit");
        switchTo().window(0);
    }

    @DisplayName("partialTextSearch")
//    @Disabled
    @Test
    void searchByPartialText  (){
        homeClientPage.partialTextSearch();
    }






}


/**questions
 * flex after html elements. How flex affects the Locators elements
 * list of devtools shortcuts
 *AllOrdersClientPage -  look at example of openPage and isOpened methods.
 * isOpened method should contain all the checks of static information available on the page
 //$(".breadcrumb li:nth-child(2)"), - learn how to use :nth-child
 put all staticText verifications in one method - .open
 build breadcrumbs -different set on each own page. Within  TheUserRole - each page should contain its own breadcrumbs.
 build header - different set on each own page. Within  the TheUserRole - each page should contain its own header.
 build sidebar - different set on each own page. Within  the TheUserRole - each page should contain its own sidebar.

 *
 * */













    
    
   
    






