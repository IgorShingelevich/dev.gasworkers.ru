package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationComponent.InputPasswordStepRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.SuccessRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent.FifthStepSelfEmployedRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent.FirstStepSelfEmployedRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent.SecondStepSelfEmployedRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent.ThirdStepSelfEmployedRegistrationComponent;

public class RegistrationSelfEmployedPage extends BaseSelfEmployedPage{


    public final FirstStepSelfEmployedRegistrationComponent firstStep;
    public final SecondStepSelfEmployedRegistrationComponent secondStep;
    public final ThirdStepSelfEmployedRegistrationComponent thirdStep;
    public final InputPasswordStepRegistrationComponent forthStep;
    public final FifthStepSelfEmployedRegistrationComponent fifthStep;
    public final SuccessRegistrationComponent successRegistration;
    public RegistrationSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        firstStep = new FirstStepSelfEmployedRegistrationComponent(browser);
        secondStep = new SecondStepSelfEmployedRegistrationComponent(browser);
        thirdStep = new ThirdStepSelfEmployedRegistrationComponent(browser);
        forthStep = new InputPasswordStepRegistrationComponent(browser);
        fifthStep = new FifthStepSelfEmployedRegistrationComponent(browser);
        successRegistration = new SuccessRegistrationComponent(browser);
    }


}
