package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationComponent.SuccessRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent.FirstStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent.ForthStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent.SecondStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.InputPasswordStepRegistrationComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.RegularRegistrationConfirmationCodeComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationClientPage extends BaseClientPage {

    public final RegularRegistrationConfirmationCodeComponent confirmationCode;
    
    public final FirstStepClientRegistrationComponent firstStepClient;
    public final SecondStepClientRegistrationComponent secondStep;
    public final InputPasswordStepRegistrationComponent thirdStep;
    public final ForthStepClientRegistrationComponent forthStep;
    public final SuccessRegistrationComponent successRegistrationStep;

    public RegistrationClientPage(RoleBrowser browser) {
        super(browser);
        confirmationCode = new RegularRegistrationConfirmationCodeComponent(browser);
        firstStepClient = new FirstStepClientRegistrationComponent(browser);
        secondStep = new SecondStepClientRegistrationComponent(browser);
        thirdStep = new InputPasswordStepRegistrationComponent(browser);
        forthStep = new ForthStepClientRegistrationComponent(browser);
        successRegistrationStep = new SuccessRegistrationComponent(browser);
    }

    SelenideElement
        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("???????????? ???????????????? ?? ???????????????????? ????????"),
        backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("???????????? ???????????????? ?? ?????????????????????? ????????");

    public void clickNext () {
        stepWithRole("???????????? ???? ???????????????? ???????????? ??????????" , () -> {
            forwardButtonLocator.shouldHave(text("??????????")).shouldNotHave(attribute("disabled"));
            forwardButtonLocator.click();
        });
    }

    public void clickCancel () {
        stepWithRole("???????????? ???? ???????????????? ???????????? ????????????" , () -> {
            backButtonLocator.shouldHave(text("????????????")).shouldNotHave(attribute("disabled"));
            backButtonLocator.click();
        });
    }



}
