package ru.gasworkers.dev.pages.components.registrationStepComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;

public class HeaderRegistrationComponent extends BaseComponent {
    public HeaderRegistrationComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        logoLocator = driver.$("div.logo a").as("Логотип"),
        alreadyRegisteredLinkLocator = driver.$("div.link-gray-wrap a").as("Ссылка Уже зарегистрированы?");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что хедер регистрации отображается", () -> {
            stepWithRole("Убедиться, что отображается логотип", () -> {
                logoLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображается ссылка Уже зарегистрированы?", () -> {
                alreadyRegisteredLinkLocator.shouldBe(visible);
            });
        });
    }

    public void clickAlreadyRegistered () {
        stepWithRole("Кликнуть по ссылке Уже зарегистрированы?", () -> {
            alreadyRegisteredLinkLocator.click();
        });
    }

}
