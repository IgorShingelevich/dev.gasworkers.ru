package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.InputValidationSharedComponent;

public abstract class BaseOrderCardComponent extends BaseComponent {
    public final InputValidationSharedComponent validation;

    public BaseOrderCardComponent(RoleBrowser browser) {
        super(browser);
        validation = new InputValidationSharedComponent(browser);
    }

}