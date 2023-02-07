package ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class StepperComponent extends BaseComponent {

    public StepperComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
     stepTitleLocator = driver.$("div.gas-stepper-tip__title").as("Заголовок Шаг заказа");

    public void checkStepTitle(String title) {
        stepWithRole("Убедиться, что заголовок шага равен " + title, () ->
                stepTitleLocator.shouldHave(text(title))
        );
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Шаг заказа загружен", () ->
                stepTitleLocator.shouldBe(visible)
        );
    }

}
