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

    public boolean isDisabled(SelenideElement closestBoxLocator) {
        return stepWithRole("Убедиться, что главная кнопка неактивна в: " + closestBoxLocator.getAlias(), () -> {
            return closestBoxLocator.$("button.btn.btn-primary").is(Condition.disabled);
        });
    }

    public boolean isEnabled() {
        return stepWithRole("Убедиться, что главная кнопка активна", () -> {
            return buttonLocator.is(Condition.enabled);
        });
    }
    public boolean isEnabled(SelenideElement closestBoxLocator) {
        return stepWithRole("Убедиться, что главная кнопка активна в: " + closestBoxLocator.getAlias(), () -> {
            return closestBoxLocator.$("button.btn.btn-primary").is(Condition.enabled);
        });
    }

    public void clickActive(String buttonText) {
        stepWithRole("Нажать на активную главную кнопку: " + buttonText, () -> {
            isEnabled();
            checkButtonText(buttonText);
            click1();
        });
    }

    public void clickActive(String buttonText, SelenideElement closestBoxLocator) {
        stepWithRole("Нажать на активную главную кнопку в: " + closestBoxLocator.getAlias() + " : " + buttonText, () -> {
            isEnabled(closestBoxLocator);
            checkButtonText(buttonText);
            click2(closestBoxLocator);
        });
    }

    public void checkButtonText(String text) {
        stepWithRole("Убедиться, что текст главной кнопки " + text, () -> {
            buttonTextLocator.shouldHave(Condition.text(text));
        });
    }

    public void checkButtonText(String buttonText, SelenideElement closestBoxLocator) {
        stepWithRole("Убедиться, что текст главной кнопки в: " + closestBoxLocator.getAlias() + " " + buttonText, () -> {
            closestBoxLocator.$("button.btn.btn-primary").$("span").shouldHave(Condition.text(buttonText));
        });
    }

    private void click1() {
        stepWithRole("Нажать на главную кнопку", () -> {
            buttonLocator.click();
        });
    }
    private void click2(SelenideElement closestBoxLocator) {
        stepWithRole("Нажать на главную кнопку в: " + closestBoxLocator.getAlias(), () -> {
            closestBoxLocator.$("button.btn.btn-primary").click();
        });
    }

}
