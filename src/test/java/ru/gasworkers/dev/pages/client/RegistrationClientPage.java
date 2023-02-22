package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent.FirstStepRegistrationClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent.ForthStepRegistrationClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent.SecondStepRegistrationClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent.ThirdStepRegistrationClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.RegularRegistrationConfirmationCodeComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationClientPage extends BaseClientPage {

    public final RegularRegistrationConfirmationCodeComponent confirmationCode;
    
    public final FirstStepRegistrationClientComponent firstStep;
    public final SecondStepRegistrationClientComponent secondStep;
    public final ThirdStepRegistrationClientComponent thirdStep;
    public final ForthStepRegistrationClientComponent forthStep;

    public RegistrationClientPage(RoleBrowser browser) {
        super(browser);
        confirmationCode = new RegularRegistrationConfirmationCodeComponent(browser);
        firstStep = new FirstStepRegistrationClientComponent(browser);
        secondStep = new SecondStepRegistrationClientComponent(browser);
        thirdStep = new ThirdStepRegistrationClientComponent(browser);
        forthStep = new ForthStepRegistrationClientComponent(browser);
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
