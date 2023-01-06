package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LandingPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import pages.components.sharedComponents.headerComponents.ProfileHeaderComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.profilePages.NotificationsPage;
import pages.profilePages.clientPages.*;
import pages.profilePages.clientPages.cancelPage.CancelMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoMaintenancePage;
import pages.profilePages.clientPages.infoServicesPage.InfoRepairPage;
import pages.profilePages.clientPages.infoServicesPage.InfoVideoPage;

import static com.codeborne.selenide.Selenide.*;

public class ClientFlowTests extends TestBase {

    SidebarClientComponent sidebarClient = new SidebarClientComponent();
    pages.LoginPage loginPage = new pages.LoginPage();
    TypeOrdersPage typeOrdersPage = new TypeOrdersPage();
    InfoRepairPage infoRepairPage = new InfoRepairPage();
    InfoMaintenancePage infoMaintenancePage = new InfoMaintenancePage();
    InfoVideoPage infoVideoPage = new InfoVideoPage();

    HomeClientPage homeClientPage = new HomeClientPage();

    NotificationsPage notificationsPage = new NotificationsPage();
    ActionsBlockClientComponent actionsBlockClient = new ActionsBlockClientComponent();

    ObjectsClientPage objectsClientPage = new ObjectsClientPage();
    OrdersClientPage ordersClientPage = new OrdersClientPage();
    OrderClientPage orderClientPage = new OrderClientPage();
    InvoicesClientPage invoicesClientPage = new InvoicesClientPage();

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
        actionsBlockClient.logout();
    }

    String emailClient = "shingelevich@gmail.com",
            clientName = "Шингелевич Игорь Сергеевич",
            passwordClient = "123456";





    @DisplayName("CreateNewObject")
    @Test
    void CreateNewObject() {
        homeClientPage.sidebar.objects();
        objectsClientPage.checkObjectsPageTitle();
        objectsClientPage.clickCreateNewObject();
        back();
        objectsClientPage.sidebar.home();
    }


     @DisplayName("switchToNewTab")
    @Test
    void switchToNewTab() {

        executeJavaScript("window.open('https://dev.gasworkers.ru/','_blank');");
        switchTo().window(1);
        open("https://dev.gasworkers.ru/profile/edit");
        switchTo().window(0);
    }


    @DisplayName("ClientPlaceMaintenanceRequestAndCancel")
//    @Disabled
    @Test
    void ClientPlaceMaintenanceRequestAndCancel() {
        homeClientPage.placeOrder();
        typeOrdersPage.Maintenance();
        infoMaintenancePage.nextButton();
        selectObjectMaintenancePage.pick1thObject();
        selectDateMaintenanceClientPage.pickNowDateAM();
        selectDateMaintenanceClientPage.submitOrder();
        selectServicePage.isOpened();
        selectServicePage.toOrder();
        orderClientPage.toMap();
        selectServicePage.toOrder();
        orderClientPage.cancelOrder();
        cancelMaintenancePage.noButton();
        orderClientPage.cancelOrder();
        cancelMaintenancePage.yesButton();
        homeClientPage.isOpened();
        forward();
        back();
    }
















}
















    
    
   
    






