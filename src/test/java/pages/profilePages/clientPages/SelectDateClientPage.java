package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.DatePickerOrderComponent;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectDateClientPage {

//    DatePickerOrderComponent datePicker = new DatePickerOrderComponent();
    private final String SELECT_DATE_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";


    SelenideElement titleLocator = $(".page-content .text-center"),    //$(".page-content .text-center h4"),
        submitOrderButtonLocator = $(".page-content .text-center"),
                    //$(".page-content .text-center h4"),
                    swapObjectLinkLocator = $x("//a[@href='#']");


    public SelectDateClientPage isOpened() {
        titleLocator.shouldBe(visible).shouldHave(text(SELECT_DATE_TITLE));
        return this;
    }

    public SelectDateClientPage clickSelectDatePagePlaceOrderButton() {
        submitOrderButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectDateClientPage clickSelectDatePageChangeObjectButton() {
        swapObjectLinkLocator.shouldBe(visible).click();
        return this;
    }
    // using RandomUtils getNowDate() method to fill argument in pickNewDate() method
//    public SelectDateClientPage pickNowDateAM() {
//        datePicker.setDate(RandomUtils.getNowDate());
//        datePicker.setAMTime();
//        datePicker.submit();
//        return this;
//    }


}





