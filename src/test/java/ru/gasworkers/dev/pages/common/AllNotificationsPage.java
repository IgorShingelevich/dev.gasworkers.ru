package ru.gasworkers.dev.pages.common;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class AllNotificationsPage extends BasePage {

    public final ActionsBlockClientComponent actionBlock;
    public final SidebarClientComponent sidebar;

    public AllNotificationsPage(RoleBrowser browser) {
        super(browser);
        actionBlock = new ActionsBlockClientComponent(browser);
        sidebar = new SidebarClientComponent(browser);
    }

    private final String
        NOTIFICATIONS_TITLE = "Уведомления";

    SelenideElement
        pageTitleLocator = driver.$(".page-title"),
        readAllButtonLocator = driver.$(byTagAndText("span", "Прочитать все"));
    ElementsCollection
        notificationTitleCollection = driver.$$(".messages-list div.d-flex .flex-wrap.text-break").as("Notification title collection"),
        unreadStatusCollection = driver.$$("div.gas-box a").as("unread status");


    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
            driver.$(byTagAndText("span", "Все уведомления прочитаны")).shouldNotBe(visible);
            notificationTitleCollection.shouldHave(size(0));
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что загрузилась страница Уведомления", () -> {
            pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
            readAllButtonLocator.shouldBe(visible);
            notificationTitleCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    public AllNotificationsPage readAll() {
        stepWithRole("Прочитать все уведомления", () -> {
            stepWithRole("Нажать кнопку Прочитать все", () -> {
                readAllButtonLocator.click();
            });
            stepWithRole("Убедиться, что нет непрочитанных уведомлений", () -> {
                unreadStatusCollection.shouldHave(size(0));
            });
            stepWithRole("Убедиться, что присутствует неактивная кнопка Все уведомления прочитаны", () -> {
                driver.$(byTagAndText("span", "Все уведомления прочитаны")).shouldBe(visible);
            });
        });
        return this;
    }

    public void openNotificationByTitle(String notificationTitle) {
        stepWithRole("Открыть уведомление с заголовком: " + notificationTitle, () -> {
            notificationTitleCollection.findBy(text(notificationTitle)).click();
        });
    }

    public void checkInitialBGState(String orderNumber) {
        stepWithRole("Убедиться, что на странице в  состоянии после Фоновой регистрации пристутствует увеедомление о заказе: " + orderNumber, () -> {
            pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
            stepWithRole("Убедиться, что присутствует кнопка Прочитать все", () -> {
                readAllButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что у клиента одно уведомление", () -> {
                notificationTitleCollection.shouldHave(size(1));
                notificationTitleCollection.get(0).shouldHave(partialText(orderNumber));
            });
        });
    }
}
