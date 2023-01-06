package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.DatePickerOrderComponent;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;
import utils.RandomUtil;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectDateMaintenanceClientPage {

    public FocusHeaderComponent header = new FocusHeaderComponent();

    DatePickerOrderComponent datePicker = new DatePickerOrderComponent();
    private final String SELECT_DATE_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";


    SelenideElement
        titleLocator = $(".page-content .text-center"),    //$(".page-content .text-center h4"),
        submitButtonLocator = $(".w-100.btn.btn-primary.disable-outline"),
        swapObjectLinkLocator = $x("//a[@href='#']");


    public SelectDateMaintenanceClientPage isOpened() {
        titleLocator.shouldBe(visible).shouldHave(text(SELECT_DATE_TITLE));
        return this;
    }

    public SelectDateMaintenanceClientPage submitOrder() {
        submitButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectDateMaintenanceClientPage clickSelectDatePageChangeObjectButton() {
        swapObjectLinkLocator.shouldBe(visible).click();
        return this;
    }
    public SelectDateMaintenanceClientPage pickNowDateAM() {
        datePicker.setDate(RandomUtil.getNowDate());
        datePicker.setAMTime();
        return this;
    }


}





