package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.cssClass;

public class BoxValidationTabCommonSelfEmployedComponent extends BaseComponent {
    public BoxValidationTabCommonSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkRedBoxState(SelenideElement boxLocator) {
        stepWithRole("Убедиться что ", () -> {
            boolean isBoxLocatorHasDangerTest = false;
            boolean isFirstDivHasDangerTest = false;
            try {
                // Check if boxLocator has .danger-test class
                boxLocator.shouldHave(cssClass("danger-test"));
                isBoxLocatorHasDangerTest = true;
            } catch (AssertionError ignored) {
                // Error message not found in element, ignore and move on to next variation
            }

            // Check if the first div inside the boxLocator has .danger-test class
            if (!isBoxLocatorHasDangerTest) {
                try {
                    boxLocator.find("div").shouldHave(cssClass("danger-test"));
                    isFirstDivHasDangerTest = true;
                } catch (AssertionError ignored) {
                    // Error message not found in element, ignore and move on to next variation
                }
            }

            // Throw an exception if both checks fail
            if (!isBoxLocatorHasDangerTest && !isFirstDivHasDangerTest) {
                throw new AssertionError("Neither the boxLocator nor its  div has .danger-test class");
            }
        });
    }


    public void checkRedBoxState2(SelenideElement boxLocator) {
        stepWithRole("Проверить  состояние незаполненного бокса", () -> {
            boxLocator.shouldHave(cssClass("danger-test"));
        });
    }

    public void checkNoRedState(SelenideElement closestBox) {
        stepWithRole("Проверить  состояние заполненного бокса", () -> {
            closestBox.shouldNotHave(cssClass("danger-test"));
        });
    }


}
