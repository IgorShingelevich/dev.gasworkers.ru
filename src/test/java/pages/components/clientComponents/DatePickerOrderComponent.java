package pages.components.clientComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePickerOrderComponent extends BaseComponent {

    public DatePickerOrderComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        datePickerLocator = driver.$(".custom-date .custom-date__btn"), // Element should be visible {.custom-date>.custom-date__btn}  $(".custom-date>.custom-date__btn")
        okDayButtonLocator = driver.$x("//button[contains(.,'Ок')]"),
                //$(byTagAndText("button", "ОК")), // not work also
                // $(".daterangepicker.ltr.show-calendar.single.openscenter.linked button") - //not work anymore
        timeDropdownLocator = driver.$(".gas-select-wrap"),
        amPlaceholderLocator = driver.$(".gas-select-wrap .gas-select__header .selected-value");

    ElementsCollection
        dayCalendarLinkLocator = driver.$$(".calendars td");


    public DatePickerOrderComponent setDate(String date) {
        datePickerLocator.shouldBe(hidden).click();
        dayCalendarLinkLocator.findBy(attribute("data-date", date)).click();
        // data-date - is attribute of td element <td data-v-98ac2448="" data-date="2023-01-12" class=""> 12 </td>
        okDayButtonLocator.shouldBe(visible).click();
        return this;
    }

    public DatePickerOrderComponent setAMTime() {
        timeDropdownLocator.shouldBe(visible).click();
        timeDropdownLocator.$$(".item").get(0).click();
        amPlaceholderLocator.shouldBe(visible).shouldHave(text("09.00–15.00"));
        return this;
    }

}
