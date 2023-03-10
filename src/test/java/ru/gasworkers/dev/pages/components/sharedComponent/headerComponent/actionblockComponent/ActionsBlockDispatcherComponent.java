package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ActionsBlockDispatcherComponent extends BaseComponent {

    public ActionsBlockDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }


    SelenideElement

            actionsBlock = $(".actions-block"),

            notificationsButtonLocator = $(".actions-block .notifications.icon"),

            dropdownArrowLocator = $(".actions-block .arrow-down"),
            dropdown2Locator = $(".profile.icon"),

            profileNameABLocator = $$(".profile-menu .profile-menu__link").get(0),
            linkEditProfileABLocator = $$(".profile-menu .profile-menu__link").get(1),

            linkLogoutABLocator = $$(".profile-menu .profile-menu__link").get(2),
            allNotificationsPageTitleLocator = $(".page-title .h3.mb-2"),
            landingLogoLocator = $(".primary-header--logo");

    ElementsCollection
            notificationsListCollection = $$("div.item.item.notice-large");

    public ActionsBlockDispatcherComponent allNotifications() {
        notificationsButtonLocator.shouldBe(visible).click();

        allNotificationsPageTitleLocator.shouldHave(text("Уведомления"));
        try{
        notificationsListCollection.wait(6000l);
        }catch (Exception e){
            System.out.println("No notifications");
        }
        return this;
    }

    public ActionsBlockDispatcherComponent logout() {
        dropdownArrowLocator.shouldBe(visible).click();
        linkLogoutABLocator.shouldBe(visible).click();
        landingLogoLocator.shouldBe(visible);
        return this;
    }



}
