package ru.gasworkers.dev.pages.components.registrationComponent.clientRegistrationStepComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.HeaderRegistrationComponent;
import ru.gasworkers.dev.pages.components.registrationComponent.StepNumberRegistrationComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;

public class SecondStepClientRegistrationComponent extends BaseComponent {

    public final HeaderRegistrationComponent header;

    public final StepNumberRegistrationComponent stepNumber;

    public SecondStepClientRegistrationComponent(RoleBrowser browser) {
        super(browser);
        header = new HeaderRegistrationComponent(browser);
        stepNumber = new StepNumberRegistrationComponent(browser);
    }

    private final String
            SECOND_TITLE = "Введите код подтверждения",
            SECOND_SUBTITLE = "Введите код подтверждения из SMS или письма пришедшего к вам на электронную почту. Пожалуйста, проверьте папку СПАМ в почте";

    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");


    public void checkSecondStepFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты второго шага регистрации: " , () -> {
           stepNumber.checkStepNumber(2);
            header.checkFinishLoading();
            stepWithRole("Убедиться, что отображается заголовок: " + SECOND_TITLE , () -> {
                titleLocator.shouldHave(text(SECOND_TITLE));
            });
            stepWithRole("Убедиться, что отображается описание: " + SECOND_SUBTITLE , () -> {
                subtitleLocator.shouldHave(text(SECOND_SUBTITLE));
            });
            //TODO sendAgainPassiveLinkLocator
            stepWithRole("Убедиться, что отображается неактивная кнопка Далее" , () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldHave(attribute("disabled"));
            });
            stepWithRole("Убедиться, что отображается кнопка Назад" , () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
        });
    }

}
