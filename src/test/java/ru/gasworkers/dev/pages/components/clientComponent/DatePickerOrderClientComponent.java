package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePickerOrderClientComponent extends BaseComponent {

    public DatePickerOrderClientComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        componentLocator = driver.$("div.col-lg-9").as("Компонент Выбор Даты и Время"),
        datePickerLocator = driver.$(".custom-date .custom-date__btn").as("Компонент Выбор Даты"),
        okDayButtonLocator = driver.$x("//button[contains(.,'Ок')]"),
        timeDropdownLocator = driver.$(".gas-select-wrap").as("Компонент Выбор Времени"),
        amPlaceholderLocator = driver.$(".gas-select-wrap .gas-select__header .selected-value").as("Компонент Выбор Времени");

    ElementsCollection
        dayCalendarLinkLocator = driver.$$(".calendars td");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Выбор Даты и Время загружен", () ->
                componentLocator.shouldBe(visible)
        );
    }


    public DatePickerOrderClientComponent setDate(String date) {
        datePickerLocator.shouldBe(hidden).click();
        dayCalendarLinkLocator.findBy(attribute("data-date", date)).click();
        // data-date - is attribute of td element <td data-v-98ac2448="" data-date="2023-01-12" class=""> 12 </td>
        okDayButtonLocator.shouldBe(visible).click();
        return this;
    }

    public DatePickerOrderClientComponent setAMTime() {
        timeDropdownLocator.shouldBe(visible).click();
        timeDropdownLocator.$$(".item").get(0).click();
        amPlaceholderLocator.shouldBe(visible).shouldHave(text("09.00–15.00"));
        return this;
    }

}
