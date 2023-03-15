package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;

public class DatePickerDocumentsComponent extends BaseComponent {

        public DatePickerDocumentsComponent(RoleBrowser browser) {
            super(browser);
        }


        SelenideElement
    datepickerBoxLocator = driver.$("div.datepicker-automation").as("Блок выбора даты"),
    calendarContentLocator = driver.$("div.mx-calendar-content").as("Контент календаря");

        ElementsCollection
    allDateCollection = driver.$$("table.mx-table td").as("Все даты");

    public void setDate (SelenideElement calendarLocator, String date) {
        stepWithRole("Установить дату: " + date, () -> {
            stepWithRole("Кликнуть по полю с календварем", () -> {
                calendarLocator.click();
            });
            stepWithRole("Выбрать дату: " + date, () -> {
                calendarContentLocator.shouldBe(visible);
                allDateCollection.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(2));
                allDateCollection.findBy(Condition.attribute("title", date)).click();  //.$("div")
            });
            stepWithRole("Убедиться, что дата установлена: " + date, () -> {
                LocalDate localDate = LocalDate.parse(date);
                String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                calendarLocator.shouldHave(value(formattedDate));
            });
        });
    }


}





