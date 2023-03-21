package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.exist;

public class ValidationBellSelfEmployedComponent extends BaseComponent {
    public ValidationBellSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkRedBellState(SelenideElement closestBox){
        stepWithRole("Убедиться, что красная иконка валидации красная", () -> {
            closestBox.$("img[src='/images/notification-red.svg']").should(exist);
        });
    }

    public void checkBlueBellState(SelenideElement closestBox){
        stepWithRole("Убедиться, что зеленая иконка валидации синяя", () -> {
            closestBox.$("img[src='/images/notification-blue.svg']").should(exist);
        });
    }


}
