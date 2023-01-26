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
        mainPageTitleLocator = driver.$(".primary-header"),
        actionsBlock = driver.$(".actions-block"),
        notificationsButtonLocator = driver.$x("//div[@class='actions-block']//div[@class='notifications icon']"),//$(".notifications icon");
         messagesButtonLocator = driver.$x("//div[@class='messages icon']"), // $(".messages icon");
        dropdownArrowLocator = driver.$(".actions-block .arrow-down"),
         profileNameABLocator = driver.$(".profile-menu .profile-menu__link.text-primary"),
        linkProfileEditABLocator = driver.$x("//a[@href='/profile/edit']"),
        linkReviewABLocator = driver.$x("//a[@href='/profile/reviews']"),
        linkLogoutABLocator = driver.$$("button.profile-menu__link").findBy(text("Выйти"));

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
        stepWithRole("Перейти на страницу Уведомления", () -> {
            notificationsButtonLocator.click();
        });
        return this;
    }
}
