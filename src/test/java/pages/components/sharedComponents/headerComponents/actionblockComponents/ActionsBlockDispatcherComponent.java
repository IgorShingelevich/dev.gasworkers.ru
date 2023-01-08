package pages.components.sharedComponents.headerComponents.actionblockComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ActionsBlockDispatcherComponent {


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


    public ActionsBlockDispatcherComponent allNotifications() {
        notificationsButtonLocator.shouldBe(visible).click();
        allNotificationsPageTitleLocator.shouldHave(text("Уведомления"));
        return this;
    }

    public ActionsBlockDispatcherComponent logout() {
        dropdownArrowLocator.shouldBe(visible).click();
        linkLogoutABLocator.shouldBe(visible).click();
        landingLogoLocator.shouldBe(visible);
        return this;
    }



}
