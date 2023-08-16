package ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navCommon;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.InputValidationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent.AllButtonSharedComponent;

public abstract class BaseNavCommonTabProfilePageComponent extends BaseComponent {
    public final  AllButtonSharedComponent button;
    public final InputValidationSharedComponent validation;
    public BaseNavCommonTabProfilePageComponent(RoleBrowser browser) {
        super(browser);
        button = new AllButtonSharedComponent(browser);
        validation = new InputValidationSharedComponent(browser);
    }


}
