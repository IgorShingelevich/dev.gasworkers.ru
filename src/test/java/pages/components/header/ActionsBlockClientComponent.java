package pages.components.header;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ActionsBlockClientComponent {

    SelenideElement actionsBlock = $(".actions-block");

    public SelenideElement linkNotificationsABLocator = $x("//div[@class='actions-block']//div[@class='notifications icon']");//$(".notifications icon");

    public SelenideElement linkMessagesABLocator = $x("//div[@class='messages icon']"); // $(".messages icon");

    public SelenideElement dropdownProfileABLocator = $x("//div[@class='profile icon']"); // $(".profile icon");

    public SelenideElement profileNameABLocator = $x("//div[contains(@class, 'text-primary')]"); // $(".profile-menu__link text-primary");

    public SelenideElement linkProfileEditABLocator = $x("//a[@href='/profile/edit']"); //

    public SelenideElement linkReviewABLocator = $x("//a[@href='/profile/reviews']");

    public SelenideElement linkLogoutABLocator =$x("//button[contains(text(),'Выйти')]");

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
    public   ActionsBlockClientComponent visibilityAllElements(String ClientName) {
        visibilityActionsBlock();
        visibilityLinkNotificationsABLocator();
        visibilityLinkMessagesABLocator();
        visibilityDropdownProfileABLocator();
        clickDropdownProfileABLocator();
        visibilityProfileNameABLocator(ClientName);
        visibilityLinkProfileEditABLocator();
        visibilityLinkReviewABLocator();
        visibilityLinkLogoutABLocator();
        return this;
    }


}
