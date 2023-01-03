package pages.components.clientComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.profilePages.clientPages.SelectDateClientPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePickerOrderComponent extends SelectDateClientPage {



    SelenideElement
            datePickerLocator = $(".custom-date>.custom-date__btn"),
            okDayButtonLocator = $(".daterangepicker.ltr.show-calendar.single.openscenter.linked button"),
            timeDropdownLocator = $(".gas-select-wrap"),
            submitButtonLocator = $(".w-100.btn.btn-primary.disable-outline");

    ElementsCollection
            dayCalendarLinkLocator = $$(".calendars td");


    public void setDate(String date) {
        datePickerLocator.shouldBe(visible).click();
        dayCalendarLinkLocator.findBy(attribute("data-date", date)).click();
        // data-date - is attribute of td element <td data-v-98ac2448="" data-date="2023-01-12" class=""> 12 </td>
        okDayButtonLocator.shouldBe(visible).click();
    }

    public void setAMTime() {
        timeDropdownLocator.shouldBe(visible).click();
        timeDropdownLocator.$$(".item").get(0).click();
    }

    public void submit  () {
        submitButtonLocator.shouldBe(visible).click();
    }






}
