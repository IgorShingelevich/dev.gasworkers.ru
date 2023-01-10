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
        try {
            pageTitleLocator.wait(6000L);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        pageTitleLocator.shouldHave(text(PAGE_TITLE));
        notificationsListLocator.shouldBe(visible);
        return this;
    }

    public AllNotificationsDispatcherPage readAllNotifications() {
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



}
