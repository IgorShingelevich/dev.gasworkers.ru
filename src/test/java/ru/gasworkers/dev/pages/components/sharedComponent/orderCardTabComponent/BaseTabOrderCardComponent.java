package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.OrderState;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public abstract class BaseTabOrderCardComponent extends BaseComponent {
    public BaseTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }


}
