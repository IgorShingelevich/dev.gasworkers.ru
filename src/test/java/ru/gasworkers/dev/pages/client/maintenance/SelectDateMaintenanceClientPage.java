package ru.gasworkers.dev.pages.client.maintenance;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.DatePickerOrderClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;
import ru.gasworkers.dev.utils.RandomUtil;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public final class SelectDateMaintenanceClientPage extends BaseClientPage {

    public final DatePickerOrderClientComponent datePicker;
    public final StepperComponent stepper;


    public SelectDateMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        datePicker = new DatePickerOrderClientComponent(browser);
        stepper = new StepperComponent(browser);
    }

    private final String SELECT_DATE_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";

    SelenideElement
            titleLocator = driver.$(".page-content .text-center").as("Заголовок страницы выбора даты"),
            submitButtonLocator = driver.$(".w-100.btn.btn-primary.disable-outline").as("Кнопка Разместить Заказ"),
            objectNameLocator = driver.$("a[href*='equipment']").as("Название объекта"),
            pickAnotherObjectLinkLocator = driver.$("a[href='#']").as("Ссылка Выбрать другой объект"),
            infoBannerLocator = driver.$("div .gas-tip").as("Баннер с информацией"),
            faqSectionLocator = driver.$("div.gas-questions");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница выбора даты для объекта " + objectNameLocator.getText() + " загружена", () -> {
            titleLocator.shouldHave(text(SELECT_DATE_TITLE));
            stepper.checkFinishLoading();
            datePicker.checkFinishLoading();
            infoBannerLocator.shouldBe(visible);
            faqSectionLocator.shouldBe(visible);
            submitButtonLocator.shouldBe(visible);
        });
    }

    public SelectDateMaintenanceClientPage submitOrder() {
        stepWithRole(" Нажать на кнопку Разместить Заказ", () -> {
            submitButtonLocator.click();
        });
        return this;
    }

    public SelectDateMaintenanceClientPage pickAnotherObject() {
        stepWithRole("Выбрать другой объект", () -> {
            pickAnotherObjectLinkLocator.click();
        });
        return this;
    }

    public SelectDateMaintenanceClientPage pickNowDateAM() {
        String nowDate = RandomUtil.getNowDate();
        stepWithRole("Выбрать текущую дату и время: " + nowDate, () -> {
            datePicker.setDate(nowDate);
            datePicker.setAMTime();
            System.out.println("Select date: " + nowDate);
        });
        return this;
    }
}
