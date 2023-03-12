package ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.CodeConfirmationRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.StepNumberRegistrationComponent;

public class ThirdStepSelfEmployedRegistrationComponent extends BaseComponent {

    public final HeaderRegistrationComponent header;

    public final StepNumberRegistrationComponent stepNumber;
    public final CodeConfirmationRegistrationComponent codeConfirmation;

    public ThirdStepSelfEmployedRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
        codeConfirmation = new CodeConfirmationRegistrationComponent(browser);
    }

    public void checkFinishLoading () {
        stepWithRole("Убедиться, что третий шаг регистрации отображается", () -> {
            header.checkFinishLoading();
            stepNumber.checkStepNumber(3);
            codeConfirmation.checkFinishLoading();
        });
    }

    public void fillCode (String code) {
            codeConfirmation.fillCode(code);
    }

}
