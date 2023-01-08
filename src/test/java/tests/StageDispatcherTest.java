package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;
import pages.profilePages.dispatcherPages.*;

import static com.codeborne.selenide.Selenide.open;

public class StageDispatcherTest extends TestBase {

ActionsBlockDispatcherComponent actionBlock = new ActionsBlockDispatcherComponent();
SidebarDispatcherComponent sidebar = new SidebarDispatcherComponent();
LoginPage loginPage = new LoginPage();
HomeDispatcherPage homeDispatcherPage = new HomeDispatcherPage();
AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage();
AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage();
ProfileDispatcherPage profilePage = new ProfileDispatcherPage();
OrderCardDispatcherPage orderCardPage = new OrderCardDispatcherPage();

    String
            emailDispatcher = "test_gas_disp9@rambler.ru",
            passwordDispatcher = "123456";

    @BeforeEach
    void clientLogin() {
        open("/login");
        loginPage.open();
        loginPage.login(emailDispatcher, passwordDispatcher);
    }

        @AfterEach
        void clientLogOut() {
            actionBlock.logout();
        }


        @Test
        @DisplayName("randomRoamingDispatcherFlow")
        void randomRoamingDispatcherFlow () {
        homeDispatcherPage.switchToListView();
        homeDispatcherPage.switchToMapView();
        homeDispatcherPage.switchToListView();
        homeDispatcherPage.openFirstOrder();
        orderCardPage.sidebar.home();
        homeDispatcherPage.actionBlock.allNotifications();
        allNotificationsPage.sidebar.home();
        homeDispatcherPage.switchToListView();
        homeDispatcherPage.openFirstOrderByNumber();
        orderCardPage.sidebar.home();
        homeDispatcherPage.openRandomOrder();
        orderCardPage.sidebar.home();
        homeDispatcherPage.actionBlock.allNotifications();
        allNotificationsPage.openFirstNotification();



        }

    @DisplayName("acceptNewOrder")
//    @Disabled
    @Test
    void acceptNewOrder() {
        homeDispatcherPage.isOpened();
        homeDispatcherPage.actionBlock.allNotifications();
        allNotificationsPage.isOpened();
        allNotificationsPage.readAllNotifications();
        allNotificationsPage.openFirstNotification();
        orderCardPage.isOpened();
        orderCardPage.acceptOrder();
        orderCardPage.sidebar.home();
    }






}
