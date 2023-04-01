package ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.InputValidationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent.AllButtonSharedComponent;

import static com.codeborne.selenide.Condition.visible;

public abstract class BaseProfileSelfEmployedComponent extends BaseComponent {
    public final ToOrderContextButtonsSelfEmployedProfilePageComponent toOrderContextButtons;
    public final InputValidationSharedComponent validation;
    public final AllButtonSharedComponent button;
    public BaseProfileSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        validation = new InputValidationSharedComponent(browser);
        button = new AllButtonSharedComponent(browser);
        toOrderContextButtons = new ToOrderContextButtonsSelfEmployedProfilePageComponent(browser);
    }



}
