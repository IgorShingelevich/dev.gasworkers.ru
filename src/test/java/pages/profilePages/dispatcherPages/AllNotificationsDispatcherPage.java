package pages.profilePages.dispatcherPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllNotificationsDispatcherPage extends BaseDispatcherPage{

    private final String PAGE_TITLE = "Уведомления";

    SelenideElement

        pageTitleLocator = $(".page-title .h3.mb-2"),
        readAllButtonLocator = $(".btn.btn-primary"),
        notificationsListLocator = $("div.messages-list");

    ElementsCollection
        notificationsCollection = $$(".item-header.d-flex.justify-content-between.w-100 div.h4"),
        statusNewNotificationLinkCollection = $$(".item-header.d-flex.justify-content-between.w-100 div.h4");



    public AllNotificationsDispatcherPage isOpened() {
        pageTitleLocator.shouldHave(text(PAGE_TITLE));
        notificationsListLocator.shouldBe(visible);
        return this;
    }

    public AllNotificationsDispatcherPage readAllNotifications() {
        readAllButtonLocator.shouldBe(visible).shouldBe(enabled).shouldBe(appear).click();
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



}
