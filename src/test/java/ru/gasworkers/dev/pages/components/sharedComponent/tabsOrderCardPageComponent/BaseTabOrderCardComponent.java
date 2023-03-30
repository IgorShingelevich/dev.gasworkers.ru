package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.util.Objects;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;

public abstract class BaseTabOrderCardComponent extends BaseComponent {
    public BaseTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkErrorMsgInBox(SelenideElement element, String errorMsg) {
        stepWithRole("Убедиться, что присутствует сообщение об ошибке у поля: " + element.getAlias(), () -> {
            boolean errorMsgFound = false;
            try {
                element.shouldHave(text(errorMsg)).isDisplayed();
                errorMsgFound = true;
            } catch (AssertionError ignored) {
                // Error message not found in element, ignore and move on to next variation
            }

            if (!errorMsgFound) {
                try {
                    element.sibling(0).shouldHave(text(errorMsg)).isDisplayed();
                    errorMsgFound = true;
                } catch (IndexOutOfBoundsException | AssertionError e) {
                    // Error message not found in element's sibling either, throw exception
                    throw new AssertionError("Ошибка! Сообщение об ошибке '" + errorMsg + "' не найдено " +
                            "у поля " + element.getAlias() + " или его соседа.");
                }
            }
        });
    }

    public void checkNoErrorMsgInBox(SelenideElement element) {
        stepWithRole("Убедиться, что отсутствует сообщение об ошибке поле не заполнено у поля: " + element.getAlias(), () -> {
            element.parent().sibling(0).shouldNot(exist);

        });
    }

}
