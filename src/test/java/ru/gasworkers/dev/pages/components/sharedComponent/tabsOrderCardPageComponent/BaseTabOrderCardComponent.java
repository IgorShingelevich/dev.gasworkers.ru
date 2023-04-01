package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.InputValidationSharedComponent;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;

public abstract class BaseTabOrderCardComponent extends BaseComponent {
    public final InputValidationSharedComponent validation;
    public BaseTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        validation = new InputValidationSharedComponent(browser);
    }

}