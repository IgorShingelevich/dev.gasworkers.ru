package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import pages.components.sharedComponent.profileTabsComponent.ContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.PasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.commonTab.CommonClientProfileComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfileClientPage extends BaseClientPage{

    public final SidebarClientComponent sidebar;
    public final ActionsBlockClientComponent actionsBlock;
    public final CommonClientProfileComponent commonTab;
    public final ContactsTabProfileComponent contactsTab;
    public final PasswordTabProfileComponent passwordTab;
    public final NotificationsTabProfileComponent notificationsTab;

    public ProfileClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
        commonTab = new CommonClientProfileComponent(browser);
        contactsTab = new ContactsTabProfileComponent(browser);
        passwordTab = new PasswordTabProfileComponent(browser);
        notificationsTab = new NotificationsTabProfileComponent(browser);
    }
    ElementsCollection
        navTabsCollection = driver.$$(".navigation-block li").as("Вкладки профиля");
    SelenideElement
        pageTitileLocator = driver.$("div h1").as("Заголовок страницы"),
        navCommonTabLocator = navTabsCollection.get(0).as("Общие данные"),
        navContactsTabLocator = navTabsCollection.get(1).as("Контактные данные"),
        navPasswordTabLocator = navTabsCollection.get(2).as("Пароль"),
        navNotificationsTabLocator = navTabsCollection.get(3).as("Уведомления");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Профиля загружена", () -> {
            pageTitileLocator.shouldHave(text("Профиль"));
            navCommonTabLocator.shouldHave(text("Общие данные"));
            navContactsTabLocator.shouldHave(text("Контактные данные"));
            navPasswordTabLocator.shouldHave(text("Пароль"));
            navNotificationsTabLocator.shouldHave(text("Уведомления"));
        });
    }

    public void navCommon () {
        stepWithRole("Перейти на вкладку Общие данные", () -> {
            navCommonTabLocator.click();
        });
    }

    public void navContacts () {
        stepWithRole("Перейти на вкладку Контактные данные", () -> {
            navContactsTabLocator.click();
        });
    }
    public void navPassword () {
        stepWithRole("Перейти на вкладку Пароль", () -> {
            navPasswordTabLocator.click();
        });
    }
    public void navNotifications () {
        stepWithRole("Перейти на вкладку Уведомления", () -> {
            navNotificationsTabLocator.click();
        });
    }






}
