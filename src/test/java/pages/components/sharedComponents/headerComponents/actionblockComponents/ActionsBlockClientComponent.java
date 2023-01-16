package pages.components.sharedComponents.headerComponents.actionblockComponents;

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
             dropdown2Locator = $(".profile.icon"),

             profileNameABLocator = $(".profile-menu .profile-menu__link.text-primary"),
        //$x("//div[contains(@class, 'text-primary')]"); // $(".profile-menu__link text-primary");

            linkProfileEditABLocator = $x("//a[@href='/profile/edit']"),

            linkReviewABLocator = $x("//a[@href='/profile/reviews']"),

            linkLogoutABLocator = $$("button.profile-menu__link").findBy(text("Выйти"));
                    // $(".profile-wrap active .back-ic");
                    //$$("button.profile-menu__link").findBy(text("Выйти"));
            //$x("//button[contains(text(), 'Выйти')]");
            // $$x(("//button[contains(text(),'Выйти')]")).get(0);
            //
                //$x("//button[@class='profile-menu__link'][contains(.,'Выйти')]");
            //$x("//div[@class='actions-block']
                // div[@class='profile-menu__link'] //button");




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

    /*public ActionsBlockClientComponent clickDropdown2() {
        dropdown2Locator.shouldBe(interactable);
        dropdown2Locator.click();
        return this;
    }*/



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
    public ActionsBlockClientComponent verifyLocators() {
        actionsBlock.shouldBe(visible);
        notificationsButtonLocator.shouldBe(visible);
        messagesButtonLocator.shouldBe(visible);
        profileNameABLocator.shouldBe(hidden);
        linkProfileEditABLocator.shouldBe(hidden);
        linkReviewABLocator.shouldBe(hidden);
        linkLogoutABLocator.shouldBe(hidden);
        dropdownArrowLocator.shouldBe(visible);
        dropdown2Locator.shouldBe(visible);
        dropdownArrowLocator.click();
        profileNameABLocator.shouldBe(visible);
        linkProfileEditABLocator.shouldBe(visible);
        linkReviewABLocator.shouldBe(visible);
        linkLogoutABLocator.shouldBe(visible);
        dropdownArrowLocator.click();
        return this;
    }


    public ActionsBlockClientComponent allNotifications() {
        notificationsButtonLocator.shouldBe(visible);
        notificationsButtonLocator.click();
        return this;
    }
}
