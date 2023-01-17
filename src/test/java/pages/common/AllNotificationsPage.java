package pages.common;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AllNotificationsPage extends BasePage {

    public AllNotificationsPage(RoleBrowser browser) {
        super(browser);
    }

    private final String
        NOTIFICATIONS_TITLE = "Уведомления";

    SelenideElement
        pageTitleLocator = $(".page-title"),
        firstNotificationTabLocator = $$(".messages-list div.item.item.notice-large").first(),
        readAllButtonLocator = $(".page-content button.btn"),

        firstNotificationLinkLocator = $$(".messages-list div.item.item.notice-large .h4").first(),
        firstNotificationTextLocator = $$(".item.item.notice-large .text.w-100.text-left").first(),
        firstNotificationStatusLocator = $$(".item.item.notice-large .text.w-25").first();



    public AllNotificationsPage verifyLocators() {
        pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
        readAllButtonLocator.shouldBe(visible);
        firstNotificationLinkLocator.shouldBe(visible);
        firstNotificationTextLocator.shouldBe(visible);
        firstNotificationStatusLocator.shouldBe(visible);
        return this;
    }

    public AllNotificationsPage isOpened() {
        pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
        firstNotificationTabLocator.should(appear, Duration.ofSeconds(20));
        readAllButtonLocator.shouldBe(visible);
        return this;
    }

    public AllNotificationsPage readAll() {
        //clic the button readAllButtonLocator if readAllButtonLocator is not clicable - proceed the test
        if (readAllButtonLocator.isDisplayed()) {
            readAllButtonLocator.click();
        }
        else {
            System.out.println("No unread notifications");
        }
        return this;
    }




}