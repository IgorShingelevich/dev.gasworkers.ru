package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class MainButtonSharedComponent extends BaseComponent {
    public MainButtonSharedComponent(RoleBrowser browser) {
        super(browser);
    }

SelenideElement
    buttonLocator = driver.$("button.btn.btn-primary").as("главная кнопка"),
    buttonTextLocator = buttonLocator.$("span").as("текст главной кнопки");


    public boolean isDisabled() {
        return stepWithRole("Убедиться, что главная кнопка неактивна", () -> {
          return   buttonLocator.is(Condition.disabled);
        });
    }

    public boolean isEnabled() {
        return stepWithRole("Убедиться, что главная кнопка активна", () -> {
            return buttonLocator.is(Condition.enabled);
        });
    }



    // Overloaded clickActive method without componentBoxLocator
    public void clickActive(String buttonText) {
        stepWithRole("Нажать на активную главную кнопку: " + buttonText, () -> {
            isEnabled();
            checkButtonText(buttonText);
            click1();
        });
    }

    // Overloaded clickActive method with componentBoxLocator
    public void clickActive(String buttonText, SelenideElement componentBoxLocator) {
        stepWithRole("Нажать на активную главную кнопку: " + buttonText, () -> {
            isEnabled();
            checkButtonText(buttonText);
            click2(componentBoxLocator);
        });
    }

    private void checkButtonText(String text) {
        stepWithRole("Убедиться, что текст главной кнопки равен " + text, () -> {
            buttonTextLocator.shouldHave(Condition.text(text));
        });
    }
// first click implementation
    private void click1() {
        stepWithRole("Нажать на главную кнопку", () -> {
            buttonLocator.click();
        });
    }

    // second click implementation
    private void click2(SelenideElement componenetBoxLocator) {
        stepWithRole("Нажать на главную кнопку", () -> {
            componenetBoxLocator.$("button.btn.btn-primary").click();
        });
    }



}
