package pages.profile.client;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectDateClientPage {
    private final String SELECT_DATE_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";


    SelenideElement TitleLocator = $(".page-content .text-center"),    //$(".page-content .text-center h4"),
        PlaceOrderButtonLocator = $(".page-content .text-center"),
                    //$(".page-content .text-center h4"),
        ChangeObjectButtonLocator = $x("//a[@href='#']");


    public SelectDateClientPage isOpened() {
        TitleLocator.shouldBe(visible).shouldHave(text(SELECT_DATE_TITLE));
        return this;
    }

    public SelectDateClientPage clickSelectDatePagePlaceOrderButton() {
        PlaceOrderButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectDateClientPage clickSelectDatePageChangeObjectButton() {
        ChangeObjectButtonLocator.shouldBe(visible).click();
        return this;
    }



}
