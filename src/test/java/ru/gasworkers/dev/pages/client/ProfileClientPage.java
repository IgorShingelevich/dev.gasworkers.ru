package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavContactsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavNotificationsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavPasswordTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon.NavCommonTabClientProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfileClientPage extends BaseClientPage{

    public final ClientSidebarComponent sidebar;
    public final ClientActionsBlockComponent actionsBlock;
    public final NavCommonTabClientProfilePageComponent navCommonTab;
    public final NavContactsTabProfilePageComponent navContactsTab;
    public final NavPasswordTabProfilePageComponent navPasswordTab;
    public final NavNotificationsTabProfilePageComponent navNotificationsTab;

    public ProfileClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
        navCommonTab = new NavCommonTabClientProfilePageComponent(browser);
        navContactsTab = new NavContactsTabProfilePageComponent(browser);
        navPasswordTab = new NavPasswordTabProfilePageComponent(browser);
        navNotificationsTab = new NavNotificationsTabProfilePageComponent(browser);
    }
    ElementsCollection
        navTabsCollection = driver.$$(".navigation-block li").as("Вкладки профиля");
    SelenideElement
        pageTitleLocator = driver.$("div h1").as("Заголовок страницы"),
        navCommonTabLocator = navTabsCollection.get(0).as("Общие данные"),
        navContactsTabLocator = navTabsCollection.get(1).as("Контактные данные"),
        navPasswordTabLocator = navTabsCollection.get(2).as("Пароль"),
        navNotificationsTabLocator = navTabsCollection.get(3).as("Уведомления");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Профиля загружена", () -> {
            pageTitleLocator.shouldHave(text("Профиль"));
            navCommonTabLocator.shouldHave(text("Общие данные"));
            navContactsTabLocator.shouldHave(text("Контактные данные"));
            navPasswordTabLocator.shouldHave(text("Пароль"));
            navNotificationsTabLocator.shouldHave(text("Уведомления"));
        });
        stepWithRole("Убедиться, что виджет Живой чат не отображается", () -> {
            driver.$("#jivo-iframe-container").shouldNotBe(visible, Duration.ofSeconds(10));
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
