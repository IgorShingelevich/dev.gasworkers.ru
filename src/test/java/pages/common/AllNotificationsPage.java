package pages.common;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class AllNotificationsPage extends BasePage {

    public AllNotificationsPage(RoleBrowser browser) {
        super(browser);
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
            if (readAllButtonLocator.isDisplayed()) {
                readAllButtonLocator.click();
            }
            else {
                System.out.println("No unread notifications");
            }
            unreadStatusCollection.shouldHave(size(0));
            stepWithRole("Убедиться, что неактивная кнопка Все уведомления прочитаны", () -> {
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




}
