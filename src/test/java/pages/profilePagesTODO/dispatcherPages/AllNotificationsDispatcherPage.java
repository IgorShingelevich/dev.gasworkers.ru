package pages.profilePagesTODO.dispatcherPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllNotificationsDispatcherPage extends BaseDispatcherPage{

    private final String PAGE_TITLE = "Уведомления";

    SelenideElement

        pageTitleLocator = $(".page-title .h3.mb-2"),
        readAllButtonLocator = $(".btn.btn-primary"),
        notificationsListLocator = $("div.messages-list"),
        firstNotificationLocator = $$(".item-header.d-flex.justify-content-between.w-100 div.h4").get(0);

    ElementsCollection
        notificationsCollection = $$(".item-header.d-flex.justify-content-between.w-100 div.h4"),
        statusNewNotificationLinkCollection = $$(".item-header.d-flex.justify-content-between.w-100 div.h4");



    public AllNotificationsDispatcherPage isOpened() {
        firstNotificationLocator.should(appear, Duration.ofSeconds(10));
        return this;
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



}
