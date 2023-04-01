package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.JivoMessengerComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public final class LoginPage extends BasePage {
    public final JivoMessengerComponent jivoMessengerComponent;

    public LoginPage(RoleBrowser browser) {
        super(browser);
        jivoMessengerComponent = new JivoMessengerComponent(browser);
    }

    SelenideElement
    jivoWidgetLocator = driver.$("jdiv jdiv jdiv jdiv jdiv jdiv").as("Виджет JivoSite");

    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () -> {
            driver.open("/login");
            jivoMessengerComponent.presenceOfJivoIcon();
        });
        return this;
    }

    public void login(String email, String password) {
        driver.$(".title h3").shouldHave(text("Войдите в личный кабинет"));
        stepWithRole("Ввести почту: " + email, () -> {
            driver.$("input[placeholder=E-mail]").as("Поле ввода почты").click();
            driver.$("input[placeholder=E-mail]").setValue(email);
            driver.$("input[placeholder=E-mail]").pressEnter();
        });
        stepWithRole("Ввести пароль: " + password, () -> {
            driver.$("input[placeholder=Пароль]").as("Поле ввода пароля").click();
            driver.$("input[placeholder=Пароль]").setValue(password);
            driver.$("input[placeholder=Пароль]").pressEnter();
        });
        stepWithRole("Нажать активную кнопку Войти", () -> {
            driver.$("button.mb-2.btn").shouldNotBe(disabled).as("Кнопка Войти");

            driver.$(byTagAndText("span", "Далее")).click();
            System.out.println("login as " + email + " " + password);
        });
    };
}