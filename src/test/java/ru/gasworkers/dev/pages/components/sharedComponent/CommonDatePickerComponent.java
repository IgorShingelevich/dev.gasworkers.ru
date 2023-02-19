package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.utils.RandomUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.assertj.core.error.ShouldHave.shouldHave;

public class CommonDatePickerComponent extends BaseComponent {

    public CommonDatePickerComponent(RoleBrowser browser){
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
            cancelButtonLocator.shouldHave(text("Отмена"));
        });
    }

    public void setTodayDate() {
        stepWithRole("Выбрать сегодняшнюю дату", () -> {
            todayButtonLocator.click();
            okButtonLocator.click();
            stepWithRole("Проверить, что дата выбрана: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), () -> {
                filledDateFieldLocator.shouldHave(Condition.text(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))));
            });
        });
    }


    //TODO implement CommonDatePickerComponent in all Profiles CommonTabs. All Masters equipmentDocs forms.
}
