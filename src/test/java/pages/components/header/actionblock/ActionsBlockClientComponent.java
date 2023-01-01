package pages.components.header.actionblock;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ActionsBlockClientComponent {

    /**<div data-v-af68ad0e="" class="actions-block"><div data-v-af68ad0e="" class="notifications icon"><!----></div> <div data-v-af68ad0e="" class="messages icon"><!----></div> <div data-v-af68ad0e="" class="profile-wrap active"><div data-v-af68ad0e="" class="profile icon"></div> <span data-v-af68ad0e="" class="arrow-down"></span> <div data-v-af68ad0e="" class="profile-menu"><div data-v-af68ad0e="" class="profile-menu__link text-primary">Шингелевич Игорь Сергеевич</div> <hr data-v-af68ad0e=""> <a data-v-af68ad0e="" href="/profile/edit" class="profile-menu__link">
     Профиль
     </a> <a data-v-af68ad0e="" href="/profile/reviews" class="profile-menu__link">
     Мои отзывы
     </a> <!----> <hr data-v-af68ad0e=""> <button data-v-af68ad0e="" class="profile-menu__link">
     Выйти <span data-v-af68ad0e="" class="back-ic"></span></button></div></div></div>
     * */

    SelenideElement actionsBlock = $(".actions-block");

    public SelenideElement linkNotificationsABLocator = $x("//div[@class='actions-block']//div[@class='notifications icon']");//$(".notifications icon");

    public SelenideElement linkMessagesABLocator = $x("//div[@class='messages icon']"); // $(".messages icon");

    public SelenideElement dropdownABLocator = $(".actions-block .arrow-down");
    public SelenideElement dropdown2ABLocator = $(".profile icon");

    public SelenideElement profileNameABLocator = $(".profile-menu .profile-menu__link text-primary");
        //$x("//div[contains(@class, 'text-primary')]"); // $(".profile-menu__link text-primary");

    public SelenideElement linkProfileEditABLocator = $x("//a[@href='/profile/edit']"); //

    public SelenideElement linkReviewABLocator = $x("//a[@href='/profile/reviews']");

    public SelenideElement linkLogoutABLocator = $$("button.profile-menu__link").findBy(text("Выйти"));
            // $(".profile-wrap active .back-ic");
            //$$("button.profile-menu__link").findBy(text("Выйти"));
    //$x("//button[contains(text(), 'Выйти')]");
    // $$x(("//button[contains(text(),'Выйти')]")).get(0);
    //
        //$x("//button[@class='profile-menu__link'][contains(.,'Выйти')]");
    //$x("//div[@class='actions-block']
        // div[@class='profile-menu__link'] //button");

    SelenideElement mainPageHeaderLocator = $(".primary-header");


    public ActionsBlockClientComponent isVisible() {
        actionsBlock.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent isVisibleNotificationsButton() {
        linkNotificationsABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent isVisibleMessagesButton() {
        linkMessagesABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent isVisibleDropdown() {
        dropdownABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent clickDropdown() {
        dropdownABLocator.shouldBe(interactable);
        dropdownABLocator.click();
        return this;
    }

    public ActionsBlockClientComponent clickDropdown2() {
        dropdown2ABLocator.shouldBe(interactable);
        dropdown2ABLocator.click();
        return this;
    }

    /*public ActionsBlockClientComponent isVisibleProfileName(String clientName) {
        profileNameABLocator.shouldHave(text(clientName)).shouldBe(visible);
        return this;
    } */
    public ActionsBlockClientComponent isVisibleProfileName() {
        profileNameABLocator.shouldHave().shouldBe(hidden);
        return this;
    }

    public ActionsBlockClientComponent isVisibleProfileButton() {
        linkProfileEditABLocator.shouldBe(hidden);
        return this;
    }

    public ActionsBlockClientComponent isVisibleReviewButton() {
        linkReviewABLocator.shouldBe(hidden);
        return this;
    }

    public ActionsBlockClientComponent isVisibleLogoutButton() {
        linkLogoutABLocator.shouldBe(hidden);
        return this;
    }
    public ActionsBlockClientComponent logout() {
        dropdownABLocator.shouldBe(exist);
        dropdownABLocator.shouldBe(interactable);
        dropdownABLocator.click();
        linkLogoutABLocator.shouldBe(exist);
        linkLogoutABLocator.shouldBe(interactable);
        linkLogoutABLocator.click();
        mainPageHeaderLocator.shouldBe(visible);

        return this;
    }
    public   ActionsBlockClientComponent visibilityAllElements(String clientName) {
        isVisible();
        isVisibleNotificationsButton();
        isVisibleMessagesButton();
        isVisibleDropdown();
        clickDropdown();
        isVisibleProfileName();
        isVisibleProfileButton();
        isVisibleReviewButton();
        isVisibleLogoutButton();
        return this;
    }


}
