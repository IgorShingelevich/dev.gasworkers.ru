package pages.components.sharedComponents.headerComponents.actionblockComponents;

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

    public SelenideElement notificationsLinkLocator = $x("//div[@class='actions-block']//div[@class='notifications icon']");//$(".notifications icon");

    public SelenideElement messagesLinkLocator = $x("//div[@class='messages icon']"); // $(".messages icon");

    public SelenideElement dropdownLocator = $(".actions-block .arrow-down");
    public SelenideElement dropdown2Locator = $(".profile.icon");

    public SelenideElement profileNameABLocator = $(".profile-menu .profile-menu__link.text-primary");
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




    public ActionsBlockClientComponent clickDropdown() {
        dropdownLocator.shouldBe(interactable);
        dropdownLocator.click();
        return this;
    }

    public ActionsBlockClientComponent clickDropdown2() {
        dropdown2Locator.shouldBe(interactable);
        dropdown2Locator.click();
        return this;
    }

    /*public ActionsBlockClientComponent isVisibleProfileName(String clientName) {
        profileNameABLocator.shouldHave(text(clientName)).shouldBe(visible);
        return this;
    } */

    public ActionsBlockClientComponent logout() {
        dropdownLocator.shouldBe(exist);
        dropdownLocator.shouldBe(interactable);
        dropdownLocator.click();
        linkLogoutABLocator.shouldBe(exist);
        linkLogoutABLocator.shouldBe(interactable);
        linkLogoutABLocator.click();
        mainPageHeaderLocator.shouldBe(visible);

        return this;
    }
    public ActionsBlockClientComponent verifyLocators() {
        actionsBlock.shouldBe(visible);
        notificationsLinkLocator.shouldBe(visible);
        messagesLinkLocator.shouldBe(visible);
        profileNameABLocator.shouldBe(hidden);
        linkProfileEditABLocator.shouldBe(hidden);
        linkReviewABLocator.shouldBe(hidden);
        linkLogoutABLocator.shouldBe(hidden);
        dropdownLocator.shouldBe(visible);
        dropdown2Locator.shouldBe(visible);
        dropdownLocator.click();
        profileNameABLocator.shouldBe(visible);
        linkProfileEditABLocator.shouldBe(visible);
        linkReviewABLocator.shouldBe(visible);
        linkLogoutABLocator.shouldBe(visible);
        dropdownLocator.click();
        return this;
    }







}
