package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class AllNotificationsDispatcherPage extends BaseDispatcherPage {

    private final DispatcherSidebarComponent sidebarDispatcher;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public AllNotificationsDispatcherPage (RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new DispatcherSidebarComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }
    private final String PAGE_TITLE = "Уведомления";

    SelenideElement

        pageTitleLocator = driver.$(".page-title .h3.mb-2"),
        readAllButtonLocator = driver.$(byTagAndText("span", "Прочитать все")),
        notificationsListLocator = driver.$("div.messages-list"),
        firstNotificationLocator = driver.$$(".item-header.d-flex.justify-content-between.w-100 div.h4").get(0);

    ElementsCollection
        notificationsCollection = driver.$$(".item-header.d-flex.justify-content-between.w-100 div.h4"),
        statusNewNotificationLinkCollection = driver.$$(".item-header.d-flex.justify-content-between.w-100 div.h4");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница " + PAGE_TITLE + " загрузилась", () -> {
            checkUrl();
            pageTitleLocator.shouldHave(text(PAGE_TITLE));
        });
    }


    public AllNotificationsDispatcherPage readAllNotifications() {
        firstNotificationLocator.should(appear, Duration.ofSeconds(10));
        readAllButtonLocator.should(appear, Duration.ofSeconds(10));
        readAllButtonLocator.shouldBe(visible).click();
//        try {
//            notificationsCollection.wait(6000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        notificationsCollection.get(0).shouldBe(visible);
        return this;
    }

    public AllNotificationsDispatcherPage clickFirstNewStatusNotification(){
        statusNewNotificationLinkCollection.first().click();
        return this;
    }

    public AllNotificationsDispatcherPage openFirstNotification(){
        notificationsCollection.first().click();
        return this;
    }


    public void open() {
//        https://dev.gasworkers.ru/profile/notifications
        stepWithRole("Открытие страницы " + PAGE_TITLE, () -> {
            driver.open("/profile/notifications");
        });
    }

    public void checkUrl() {
        stepWithRole("Проверка URL страницы " + PAGE_TITLE, () -> {
            urlChecker.urlContains("/profile/notifications");
        });
    }
}
