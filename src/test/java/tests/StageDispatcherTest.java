//package tests;
//
//import org.junit.jupiter.api.*;
//import pages.common.LoginPage;
//import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
//import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;
//import pages.profilePages.dispatcherPages.*;
//
//import static com.codeborne.selenide.Selenide.*;
//
//public class StageDispatcherTest extends TestBase {
//
//
//    @BeforeEach
//    void clientLogin() {
//        open("/login");
//        loginPage.open();
//        loginPage.login(emailDispatcher, passwordDispatcher);
//    }
//
////        @AfterEach
////        void clientLogOut() {
////            actionBlock.logout();
////        }
//
//
//    @Test
//    @DisplayName("randomRoamingDispatcherFlow")
//    void randomRoamingDispatcherFlow() {
//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.popUpClose();
//        homeDispatcherPage.switchToListView();
//        homeDispatcherPage.switchToMapView();
//        homeDispatcherPage.actionBlockDispatcher.allNotifications();
//        allNotificationsDispatcherPage.isOpened();
//        allNotificationsDispatcherPage.sidebarDispatcher.home();
////        back();
//        homeDispatcherPage.openFirstOrderByAction();
//        back();
//        orderCardPage.sidebarDispatcher.home();
//        homeDispatcherPage.actionBlockDispatcher.allNotifications();
//        allNotificationsDispatcherPage.sidebarDispatcher.home();
//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.switchToListView();
//        homeDispatcherPage.openFirstOrderByTitleIndex();
//        orderCardPage.sidebarDispatcher.home();
//        homeDispatcherPage.openRandomOrder();
//        orderCardPage.sidebarDispatcher.home();
//        homeDispatcherPage.actionBlockDispatcher.allNotifications();
//        allNotificationsDispatcherPage.openFirstNotification();
//
//
//    }
//
//    @DisplayName("acceptNewOrder")
////    @Disabled
//    @Test
//    void acceptNewOrder() {
//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.actionBlockDispatcher.allNotifications();
//        allNotificationsDispatcherPage.isOpened();
//        allNotificationsDispatcherPage.readAllNotifications();
//        allNotificationsDispatcherPage.openFirstNotification();
//        orderCardPage.isOpened();
//        orderCardPage.acceptOrder();
//        orderCardPage.sidebarDispatcher.home();
//    }
//
//    @DisplayName("soutGetText")
////    @Disabled
//    @Test
//    void getText() {
//        homeDispatcherPage.isOpened();
//        homeDispatcherPage.actionBlockDispatcher.allNotifications();
//        allNotificationsDispatcherPage.isOpened();
//        allNotificationsDispatcherPage.openFirstNotification();
//        orderCardPage.isOpened();
//        String orderNumberText = $("h1.h3.mb-2").getText();
//        // parse last 4 digits
//        int orderNumber = Integer.parseInt(orderNumberText.substring(orderNumberText.length() - 4));
//        System.out.println(orderNumber);
//    }
//
//
//}
