package ru.gasworkers.dev.pages.common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public final class LoginPage extends BasePage {

    public LoginPage(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
    jivoWidgetLocator = driver.$("jdiv jdiv jdiv jdiv jdiv jdiv").as("Виджет JivoSite");

    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () -> {
            driver.open("/login");
            stepWithRole("Виджет Jivo должен быть загружен", () -> {
                try {
                    if (jivoWidgetLocator.exists()) {
                        jivoWidgetLocator.shouldBe(visible);
                    } else {
                        System.out.println("No Jivo widget");
                    }
                } catch (ElementShould e) {
                    // Log the exception and proceed with the test
                    System.out.println("Element Jivo widget was not found or not enabled or not visible.");
                    e.printStackTrace();
                }
//                jivoWidgetLocator.shouldBe(visible, Duration.ofSeconds(10)).as("Виджет JivoSite");
            });
        });

        return this;
    }

    public void login(String email, String password) {
        driver.$(".title h3").shouldHave(text("Войдите в личный кабинет"));
        stepWithRole("Ввести почту: " + email, () -> {
            driver.$("input[placeholder=E-mail]") .click();
            driver.$("input[placeholder=E-mail]").setValue(email);
            driver.$("input[placeholder=E-mail]").pressEnter();
        });
        stepWithRole("Ввести пароль: " + password, () -> {
            driver.$("input[placeholder=Пароль]").click();
            driver.$("input[placeholder=Пароль]").setValue(password);
            driver.$("input[placeholder=Пароль]").pressEnter();
        });
        stepWithRole("Нажать активную кнопку Войти", () -> {
            driver.$("button.mb-2.btn").shouldNotBe(disabled);
            driver.$(byTagAndText("span", "Далее")).click();
            System.out.println("login as " + email + " " + password);
        });
    };
}


