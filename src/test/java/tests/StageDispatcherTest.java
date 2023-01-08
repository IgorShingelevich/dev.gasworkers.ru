package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;
import pages.profilePages.dispatcherPages.AllMastersDispatcherPage;
import pages.profilePages.dispatcherPages.AllNotificationsDispatcherPage;
import pages.profilePages.dispatcherPages.ProfileDispatcherPage;

import static com.codeborne.selenide.Selenide.open;

public class StageDispatcherTest extends TestBase {

ActionsBlockDispatcherComponent actionBlock = new ActionsBlockDispatcherComponent();
SidebarDispatcherComponent sidebar = new SidebarDispatcherComponent();
LoginPage loginPage = new LoginPage();
AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage();
AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage();
ProfileDispatcherPage profilePage = new ProfileDispatcherPage();

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
        @DisplayName("randomDispatcherRoamingFlow")
        void randomDispatcherRoamingFlow () {


        }






}
