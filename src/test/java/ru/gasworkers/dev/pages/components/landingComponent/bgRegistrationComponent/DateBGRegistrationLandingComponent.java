package ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DateBGRegistrationLandingComponent extends BaseComponent {

    public DateBGRegistrationLandingComponent(RoleBrowser browser){
        super(browser);
    }
 SelenideElement
    containerLocator = driver.$("div.daterangepicker").as("Контейнер с календарем"),
    filledDateFieldLocator = driver.$("div.search-option__date--title-result").as("Поле  Дата"),
    todayButtonLocator = containerLocator.$("td.today").as("Кнопка выбора сегодняшней даты"),
    okButtonLocator = containerLocator.$("button.btn-micro.mt-1.mb-1.btn.btn-primary").as("Кнопка подтверждения выбора даты"),
    cancelButtonLocator = containerLocator.$("a.link-dark-blue.medium.me-4.mt-1.mb-1.small").as("Кнопка отмены выбора даты");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Выбор Даты и Время загружен", () -> {
            containerLocator.shouldBe(visible);
            okButtonLocator.$("span").shouldHave(text("Ок"));
            cancelButtonLocator.shouldHave(text("Отменить"));
        });
    }

    /*public void setTodayDate() {
        stepWithRole("Выбрать сегодняшнюю дату", () -> {
            todayButtonLocator.click();
            okButtonLocator.click();
            stepWithRole("Проверить, что дата выбрана: " + LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy")), () -> {
                filledDateFieldLocator.shouldHave(Condition.text(LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
            });
            stepWithRole("Убедиться, что компонент Выбор Даты и Время закрыт", () -> {
                containerLocator.shouldNotBe(visible);
            });
        });
    }*/

    public void setTodayDate() {
        stepWithRole("Выбрать сегодняшнюю дату", () -> {
            todayButtonLocator.click();
            okButtonLocator.click();
            stepWithRole("Проверить, что дата выбрана: " + LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy")), () -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru", "RU"));
                String formattedDate = LocalDate.now().format(formatter);
                filledDateFieldLocator.shouldHave(Condition.text(formattedDate));
            });
            stepWithRole("Убедиться, что компонент Выбор Даты и Время закрыт", () -> {
                containerLocator.shouldNotBe(visible);
            });
        });
    }


    //TODO implement DateBGRegistrationLandingComponent in all Profiles CommonTabs. All Masters equipmentDocs forms.
}
