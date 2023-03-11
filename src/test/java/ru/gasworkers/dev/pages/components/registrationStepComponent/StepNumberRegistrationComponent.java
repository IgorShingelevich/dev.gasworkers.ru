package ru.gasworkers.dev.pages.components.registrationStepComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.cssClass;

public class StepNumberRegistrationComponent extends BaseComponent {
    public StepNumberRegistrationComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации");

    public void checkStepNumber(int stepCount) {
        stepWithRole("Убедиться, что на таймлайне выделен " + stepCount + " шаг", () -> {
            stepsCollection.get(stepCount - 1).shouldHave(cssClass("active"));
        });
    }

}
