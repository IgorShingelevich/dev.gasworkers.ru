package pages.profilePages;

import com.codeborne.selenide.SelenideElement;
import pages.profilePages.clientPages.BaseClientPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AllNotificationsPage extends BaseClientPage {

    private final String NOTIFICATIONS_TITLE = "Уведомления";

    SelenideElement
            pageTitleLocator = $(".page-title"),

            readAllButtonLocator = $(".page-content button.btn"),

            firstNotificationLinkLocator = $$(".item.item.notice-large .d-flex.flex-wrap.text-break").first(), firstNotificationTextLocator = $$(".item.item.notice-large .text.w-100.text-left").first(), firstNotificationStatusLocator = $$(".item.item.notice-large .text.w-25").first();



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
        readAllButtonLocator.shouldBe(visible);
        return this;
    }

    public AllNotificationsPage readAll() {
        readAllButtonLocator.click();
        return this;
    }




}
