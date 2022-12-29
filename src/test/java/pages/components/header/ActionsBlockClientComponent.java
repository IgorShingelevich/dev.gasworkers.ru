package pages.components.header;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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

    public SelenideElement dropdownProfileABLocator = $(".actions-block .arrow-down");

    public SelenideElement profileNameABLocator = $x("//div[contains(@class, 'text-primary')]"); // $(".profile-menu__link text-primary");

    public SelenideElement linkProfileEditABLocator = $x("//a[@href='/profile/edit']"); //

    public SelenideElement linkReviewABLocator = $x("//a[@href='/profile/reviews']");

    public SelenideElement linkLogoutABLocator = $$("button.profile-menu__link").findBy(text("Выйти"));
    //$x("//button[contains(text(), 'Выйти')]");
    // $$x(("//button[contains(text(),'Выйти')]")).get(0);
    //
        //$x("//button[@class='profile-menu__link'][contains(.,'Выйти')]");
    //$x("//div[@class='actions-block']
        // div[@class='profile-menu__link'] //button");
    public ActionsBlockClientComponent visibilityActionsBlock() {
        actionsBlock.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityLinkNotificationsABLocator() {
        linkNotificationsABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityLinkMessagesABLocator() {
        linkMessagesABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityDropdownProfileABLocator() {
        dropdownProfileABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent clickDropdownProfileABLocator() {
        dropdownProfileABLocator.click();
        return this;
    }

    public ActionsBlockClientComponent visibilityProfileNameABLocator(String ClientName) {
        profileNameABLocator.shouldHave(text(ClientName)).shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityLinkProfileEditABLocator() {
        linkProfileEditABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityLinkReviewABLocator() {
        linkReviewABLocator.shouldBe(visible);
        return this;
    }

    public ActionsBlockClientComponent visibilityLinkLogoutABLocator() {
        linkLogoutABLocator.shouldBe(visible);
        return this;
    }
    public ActionsBlockClientComponent logout() {
        dropdownProfileABLocator.click();
        linkLogoutABLocator.click();
        return this;
    }
    public   ActionsBlockClientComponent visibilityAllElements(String clientName) {
        visibilityActionsBlock();
        visibilityLinkNotificationsABLocator();
        visibilityLinkMessagesABLocator();
        visibilityDropdownProfileABLocator();
        clickDropdownProfileABLocator();
        visibilityProfileNameABLocator(clientName);
        visibilityLinkProfileEditABLocator();
        visibilityLinkReviewABLocator();
        visibilityLinkLogoutABLocator();
        return this;
    }


}
