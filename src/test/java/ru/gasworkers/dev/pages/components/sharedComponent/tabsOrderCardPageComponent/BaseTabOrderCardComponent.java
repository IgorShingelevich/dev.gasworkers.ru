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



    public void checkErrorMsgInBox3(SelenideElement element, String errorMsg) {
        stepWithRole("Убедиться, что присутствует сообщение об ошибке у поля: " + element.getAlias(), () -> {
            SelenideElement sibling = element.sibling(0);
            boolean errorMsgFound = ( element.shouldHave(text(errorMsg)).isDisplayed() ||
                     sibling.exists() && sibling.shouldHave(text(errorMsg)).isDisplayed());
            if (!errorMsgFound) {
                throw new AssertionError("Ошибка! Сообщение об ошибке '" + errorMsg + "' не найдено " +
                        "у поля " + element.getAlias() + " или его соседа.");
            }
        });
    }



    public void checkErrorMsgInBox2(SelenideElement element, String errorMsg) {
        // todo list of text in the placeholder - to specify the error message
        stepWithRole("Убедиться, что присутствует сообщение об ошибке у поля: " + element.getAlias(), () -> {
            //check that the error message is displayed or inside the element or in the .sibling(0)
            for (int i = 0; i < 2; i++) {
                if (Objects.equals(element.parent().sibling(0).text(), errorMsg)) {
                    break;
                }
                if (Objects.equals(element.parent().text(), errorMsg)) {
                    break;
                }
                element.parent().sibling(0).shouldHave(text(errorMsg));
            }

        });
    }

    public void checkNoErrorMsgInBox(SelenideElement element) {
        stepWithRole("Убедиться, что отсутствует сообщение об ошибке поле не заполнено у поля: " + element.getAlias(), () -> {
            element.parent().sibling(0).shouldNot(exist);

        });
    }

}
