package pages.profilePages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;
import pages.profilePages.clientPages.BaseClientPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class NotificationsPage extends BaseClientPage {

    private final String NOTIFICATIONS_TITLE = "Уведомления";

    SelenideElement
            pageTitleLocator = $(".page-title"),

            readAllButtonLocator = $(".page-content .btn.btn-primary.disable-outline"),

            firstNotificationLinkLocator = $$(".item.item.notice-large .d-flex.flex-wrap.text-break").first(), firstNotificationTextLocator = $$(".item.item.notice-large .text.w-100.text-left").first(), firstNotificationStatusLocator = $$(".item.item.notice-large .text.w-25").first();



    public NotificationsPage verifyLocators() {
        pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
        readAllButtonLocator.shouldBe(visible);
        firstNotificationLinkLocator.shouldBe(visible);
        firstNotificationTextLocator.shouldBe(visible);
        firstNotificationStatusLocator.shouldBe(visible);
        return this;
    }

    public NotificationsPage isOpened() {
        pageTitleLocator.shouldHave(text(NOTIFICATIONS_TITLE));
        return this;
    }
}
