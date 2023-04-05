package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;

public class DatePickerDocumentsComponent extends AllRolesSharedComponent {
    public DatePickerDocumentsComponent(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement
            datepickerBoxLocator = driver.$("div.datepicker-automation").as("Блок выбора даты"),
            calendarBlockLocator = driver.$("div.mx-calendar-content").as("Календарь");

    ElementsCollection
            allDateCollection = driver.$$("table.mx-table td").as("Все даты");

    public void setDate(SelenideElement closestBox, String date) {
        stepWithRole("Установить дату: " + date, () -> {
            stepWithRole("Убедиться, что календарь закрыт", () -> {
                calendarBlockLocator.shouldNotBe(exist);
            });
            stepWithRole("Кликнуть по полю с календарем", () -> {
                closestBox.$(".mx-input.mx-input").click(); //.datepicker-automation
            });
            stepWithRole("Выбрать дату: " + date, () -> {
                calendarBlockLocator.shouldBe(visible);
                allDateCollection.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(2));
                allDateCollection.findBy(Condition.attribute("title", date)).click();  //.$("div")
            });
            stepWithRole("Убедиться, что дата установлена: " + date, () -> {
                LocalDate localDate = LocalDate.parse(date);
                String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                closestBox.$(".mx-input").shouldHave(value(formattedDate));
                System.out.println("Установлена дата: " + formattedDate);
            });
            stepWithRole("Убедиться, что календарь закрыт", () -> {
                calendarBlockLocator.shouldNotBe(visible);
            });
            checkNoErrorMsg(closestBox);
        });
    }

    public void checkErrorMsg(SelenideElement closestBox) {
        stepWithRole("Убедиться, что присутствует ошибка в календаре - укажите дату", () -> {
            closestBox.$(".gas-input__error").shouldBe(visible);
//                    .shouldHave(Condition.text("Укажите дату"));
            //todo universal  error msg to the calendar - by checking  the text of the error
        });
        stepWithRole("Убедиться, что поле календаря пустое", () -> {
            closestBox.$(".mx-input").shouldBe(empty);
        });
    }

    public void checkNoErrorMsg(SelenideElement closestBox) {
        stepWithRole("Убедиться, что отсутствует ошибка в календаре - укажите дату", () -> {
            closestBox.$(".gas-input__error").shouldNotBe(visible);
        });
    }

    public void checkInitialState(SelenideElement closestBox) {
        stepWithRole("Убедиться, что поле календаря пустое", () -> {
            SelenideElement closestInput = closestBox.$(".mx-input").as("поле ввода даты");
            closestInput.shouldBe(empty);
            stepWithRole("Убедиться, что календарь закрыт", () -> {
                calendarBlockLocator.shouldNotBe(visible);
            });
        });
    }

    public void checkFilledState(SelenideElement dateBoxLocator) {
        stepWithRole("Убедиться, что поле календаря заполнено: "+ dateBoxLocator.$(".mx-input").getValue(), () -> {
            dateBoxLocator.$(".mx-input").shouldNotBe(empty);
        });
    }
}





