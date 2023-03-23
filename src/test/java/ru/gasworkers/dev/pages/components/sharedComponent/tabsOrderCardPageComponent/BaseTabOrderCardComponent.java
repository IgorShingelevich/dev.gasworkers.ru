package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;

public abstract class BaseTabOrderCardComponent extends BaseComponent {
    public BaseTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkErrorMsg(SelenideElement element){
        // todo list of text in the placeholder - to specify the error message
        stepWithRole("Убедиться, что присутствует сообщение об ошибке у поля: " + element.getAlias(), () -> {
            String attributeValue = element.getAttribute("placeholder");
            Integer siblingIndex = attributeValue.equals("Адрес регистрации") || attributeValue.equals("Номер квартиры") ? 1 : 0;
            element.parent().sibling(siblingIndex).shouldHave(text("Поле не заполнено"));
        });
    }

    public void checkNoErrorMsg(SelenideElement element){
        stepWithRole("Убедиться, что отсутствует сообщение об ошибке поле не заполнено у поля: " + element.getAlias(), () -> {
            element.parent().sibling(0).shouldNot(exist);
        });
    }

}
