package pages.profile.client;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectDateClientPage {

    SelenideElement
            selectDatePageTitleLocator = $(".page-content .text-center h4"),
            selectDatePagePlaceOrderButtonLocator = $(".page-content .text-center h4"),
            selectDatePageChangeObjectButtonLocator = $x("//a[@href='#']");

    public SelectDateClientPage isOpened() {
        selectDatePageTitleLocator.shouldBe(visible);
        return this;
    }

    public SelectDateClientPage clickSelectDatePagePlaceOrderButton() {
        selectDatePagePlaceOrderButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectDateClientPage clickSelectDatePageChangeObjectButton() {
        selectDatePageChangeObjectButtonLocator.shouldBe(visible).click();
        return this;
    }



}
