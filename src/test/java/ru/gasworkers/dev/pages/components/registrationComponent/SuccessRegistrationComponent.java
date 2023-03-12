package ru.gasworkers.dev.pages.components.registrationComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class SuccessRegistrationComponent extends BaseComponent {
    public SuccessRegistrationComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
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
