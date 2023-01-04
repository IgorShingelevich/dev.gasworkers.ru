package pages.components.clientComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.profilePages.clientPages.SelectDateClientPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePickerOrderComponent  {



    SelenideElement
            datePickerLocator = $(".custom-date .custom-date__btn"), // Element should be visible {.custom-date>.custom-date__btn}  $(".custom-date>.custom-date__btn")
            okDayButtonLocator = $(".daterangepicker.ltr.show-calendar.single.openscenter.linked button"),
            timeDropdownLocator = $(".gas-select-wrap"),
            amPlaceholderLocator = $(".gas-select-wrap .gas-select__header .selected-value");

    ElementsCollection
            dayCalendarLinkLocator = $$(".calendars td");


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