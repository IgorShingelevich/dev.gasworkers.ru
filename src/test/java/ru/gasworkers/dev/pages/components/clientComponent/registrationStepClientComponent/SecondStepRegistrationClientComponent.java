package ru.gasworkers.dev.pages.components.clientComponent.registrationStepClientComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;

public class SecondStepRegistrationClientComponent extends BaseComponent {
    public SecondStepRegistrationClientComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            ALREADY_REGISTERED_TEXT = "Уже зарегистрированы?",
            SECOND_TITLE = "Введите код подтверждения",
            SECOND_SUBTITLE = "Введите код подтверждения из SMS или письма пришедшего к вам на электронную почту. Пожалуйста, проверьте папку СПАМ в почте";

    ElementsCollection
            stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации");
    SelenideElement
            titleLocator = driver.$("div h4").as("Заголовок"),
            subtitleLocator = driver.$("div.description").as("Подзаголовок"),
            alreadyRegisteredLocator = driver.$("a.link-gray").as("Ссылка на страницу входа зарегистрированного пользователя"),
            forwardButtonLocator = driver.$("div button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("div button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");


    public void checkSecondStepFinishLoading () {
        stepWithRole("Убедиться, что представлены компоненты второго шага регистрации: " , () -> {
            stepWithRole("Убедиться, что на таймлайне выделен второй шаг" , () -> {
                stepsCollection.get(1).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что отображается ссылка на страницу входа зарегистрированного пользователя: " + ALREADY_REGISTERED_TEXT , () -> {
                alreadyRegisteredLocator.shouldHave(text(ALREADY_REGISTERED_TEXT));
            });
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

    public void clickAlreadyRegistered () {
        alreadyRegisteredLocator.click();
    }



}
