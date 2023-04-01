package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;

public class InputValidationSharedComponent extends AllRolesSharedComponent{
    public InputValidationSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            totalErrorCollection = driver.$$("div.gas-input__error").as("Все ошибки валидации");


    public void checkErrorMsgInBox(SelenideElement element, String errorMsg) {
        stepWithRole("Убедиться, что присутствует сообщение об ошибке у поля: " + element.getAlias(), () -> {
            boolean errorMsgFound = false;
            try {
                element
//                        .find("div.gas-input__error")
                        .shouldHave(text(errorMsg))
                        .isDisplayed();
                errorMsgFound = true;
            } catch (AssertionError ignored) {
                // Error message not found in element, ignore and move on to next variation
            }

            if (!errorMsgFound) {
                try {
                    element.sibling(0)
//                            .find("div.gas-input__error")
                            .shouldHave(text(errorMsg)).isDisplayed();
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
            boolean errorClassFound = false;
            try {
                element.shouldNotHave(cssClass("gas-input__error"));
                errorClassFound = true;
            } catch (AssertionError ignored) {
                // Error class not found in element, ignore and move on to next variation
            }
            if (!errorClassFound) {
                try {
                    element.sibling(0).shouldNotHave(cssClass("gas-input__error"));
                    errorClassFound = true;
                } catch (IndexOutOfBoundsException | AssertionError e) {
                    // Error class not found in element's sibling either, throw exception
                    throw new AssertionError("Ошибка! Класс ошибки присутствует у поля " + element.getAlias() + " или его соседа.");
                }
            }

        });
    }

    public void checkTotalErrorsCount(int count) {
        stepWithRole("Убедиться, что общее количество ошибок валидации равно: " + count, () -> {
            totalErrorCollection.shouldHave(size(count));
        });
    }

    public void checkTotalNoErrors() {
        stepWithRole("Убедиться, что отсутствуют какие либо ошибки валидации", () -> {
            totalErrorCollection.shouldHave(size(0));
        });
    }

}
//todo solve .find("div.gas-input__error") problem


