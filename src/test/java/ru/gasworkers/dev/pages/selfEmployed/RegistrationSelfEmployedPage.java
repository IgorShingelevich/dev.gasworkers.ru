package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent.FirstStepSelfEmployedRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent.SecondStepSelfEmployedRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent.ThirdStepSelfEmployedRegistrationComponent;

public class RegistrationSelfEmployedPage extends BaseSelfEmployedPage{


    public final FirstStepSelfEmployedRegistrationComponent firstStep;
    public final SecondStepSelfEmployedRegistrationComponent secondStep;
    public final ThirdStepSelfEmployedRegistrationComponent thirdStep;
    public RegistrationSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        firstStep = new FirstStepSelfEmployedRegistrationComponent(browser);
        secondStep = new SecondStepSelfEmployedRegistrationComponent(browser);
        thirdStep = new ThirdStepSelfEmployedRegistrationComponent(browser);
    }


}
