package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationStepComponent.clientRegistrationStepComponent.FirstStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.clientRegistrationStepComponent.ForthStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.clientRegistrationStepComponent.SecondStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationStepComponent.clientRegistrationStepComponent.ThirdStepClientRegistrationComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.RegularRegistrationConfirmationCodeComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationClientPage extends BaseClientPage {

    public final RegularRegistrationConfirmationCodeComponent confirmationCode;
    
    public final FirstStepClientRegistrationComponent firstStepClient;
    public final SecondStepClientRegistrationComponent secondStep;
    public final ThirdStepClientRegistrationComponent thirdStep;
    public final ForthStepClientRegistrationComponent forthStep;

    public RegistrationClientPage(RoleBrowser browser) {
        super(browser);
        confirmationCode = new RegularRegistrationConfirmationCodeComponent(browser);
        firstStepClient = new FirstStepClientRegistrationComponent(browser);
        secondStep = new SecondStepClientRegistrationComponent(browser);
        thirdStep = new ThirdStepClientRegistrationComponent(browser);
        forthStep = new ForthStepClientRegistrationComponent(browser);
    }

    SelenideElement
        forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
        backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void clickNext () {
        stepWithRole("Нажать на активную кнопку Далее" , () -> {
            forwardButtonLocator.shouldHave(text("Далее")).shouldNotHave(attribute("disabled"));
            forwardButtonLocator.click();
        });
    }

    public void clickCancel () {
        stepWithRole("Нажать на активную кнопку Отмена" , () -> {
            backButtonLocator.shouldHave(text("Отмена")).shouldNotHave(attribute("disabled"));
            backButtonLocator.click();
        });
    }

    public void checkFinishState() {
        driver.$("div.logo-small img").should(appear);
        stepWithRole("Убедиться, что представлены компоненты страницы Успешная регистрация: " , () -> {
            stepWithRole("Убедиться, что отображается заголовок: " + driver.$("div.page-content h3").getText() , () -> {
                driver.$("div.page-content h3").shouldHave(text("Поздравляем!"));
            });
            stepWithRole("Убедиться, что отображается информационный текст "+ driver.$("div.page-content").getText() , () -> {
                driver.$("div.page-content").$$("p").get(0).shouldHave(text("Вы успешно зарегистрировались в сервисе Gasworkers."));
                driver.$("div.page-content").$$("p").get(1).shouldHave(text("Через 5 секунд вы будете автоматически перенаправлены в личный кабинет"));
            });
            driver.$("div.logo-small img").should(disappear, Duration.ofSeconds(20));
        });
    }

}
