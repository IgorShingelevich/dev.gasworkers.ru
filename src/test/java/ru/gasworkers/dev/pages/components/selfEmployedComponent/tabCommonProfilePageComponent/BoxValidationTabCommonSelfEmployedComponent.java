package ru.gasworkers.dev.pages.components.selfEmployedComponent.tabCommonProfilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.cssClass;

public class BoxValidationTabCommonSelfEmployedComponent extends BaseComponent {
    public BoxValidationTabCommonSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkRedState (SelenideElement closestBox) {
        stepWithRole("Проверить  состояние анезаполненного бокса", () -> {
            closestBox.shouldHave(cssClass("diploma-danger"));
        });
    }

    public void checkFilledState (SelenideElement closestBox) {
        stepWithRole("Проверить  состояние заполненного бокса", () -> {
            closestBox.shouldNotHave(cssClass("diploma-danger"));
        });
    }


}
