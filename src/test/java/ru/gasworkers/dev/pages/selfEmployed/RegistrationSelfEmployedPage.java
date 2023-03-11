package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent.FirstStepSelfEmployedRegistrationComponent;

public class RegistrationSelfEmployedPage extends BaseSelfEmployedPage{

    public final FirstStepSelfEmployedRegistrationComponent firstStep;
    public RegistrationSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        firstStep = new FirstStepSelfEmployedRegistrationComponent(browser);
    }


}
