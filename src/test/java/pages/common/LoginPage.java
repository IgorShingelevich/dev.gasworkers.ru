package pages.common;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public final class LoginPage extends BasePage {

    public LoginPage(RoleBrowser browser) {
        super(browser);
    }

    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () -> {
            driver.open("/login");
            stepWithRole("Виджет JivoSite должен быть загружен", () -> {
                driver.$(".iconWrap_f10e._showLogo_d56c").shouldBe(visible, Duration.ofSeconds(10));
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


