package pages.components.sharedComponent.headerComponent.actionblockComponent;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ActionsBlockClientComponent extends BaseComponent {

public ActionsBlockClientComponent(RoleBrowser browser) {
    super(browser);
}

    SelenideElement
        mainPageTitleLocator = $(".primary-header"),
        actionsBlock = $(".actions-block"),
        notificationsButtonLocator = $x("//div[@class='actions-block']//div[@class='notifications icon']"),//$(".notifications icon");
         messagesButtonLocator = $x("//div[@class='messages icon']"), // $(".messages icon");
        dropdownArrowLocator = $(".actions-block .arrow-down"),
         profileNameABLocator = $(".profile-menu .profile-menu__link.text-primary"),
        linkProfileEditABLocator = $x("//a[@href='/profile/edit']"),
        linkReviewABLocator = $x("//a[@href='/profile/reviews']"),
        linkLogoutABLocator = $$("button.profile-menu__link").findBy(text("Выйти"));

    public ActionsBlockClientComponent notificationsButton() {
        notificationsButtonLocator.shouldBe(visible).click();
        return this;
    }

    public ActionsBlockClientComponent messagesButton() {
        messagesButtonLocator.shouldBe(visible).click();
        return this;
    }

    public ActionsBlockClientComponent clickDropdown() {
        dropdownArrowLocator.shouldBe(interactable);
        dropdownArrowLocator.click();
        return this;
    }

    public ActionsBlockClientComponent logout() {
        dropdownArrowLocator.shouldBe(exist);
        dropdownArrowLocator.shouldBe(interactable);
        dropdownArrowLocator.click();
        linkLogoutABLocator.shouldBe(exist);
        linkLogoutABLocator.shouldBe(interactable);
        linkLogoutABLocator.click();
        mainPageTitleLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent allNotifications() {
        notificationsButtonLocator.shouldBe(visible);
        notificationsButtonLocator.click();
        return this;
    }
}
