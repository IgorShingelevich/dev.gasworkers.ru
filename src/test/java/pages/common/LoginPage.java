package pages.common;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public final class LoginPage extends BasePage {

    public LoginPage(RoleBrowser browser) {
        super(browser);
    }

//    @Step("Открыть страницу авторизации")
    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () ->
                driver.open("/login"));
        return this;
    }

//    @Step("Авторизоваться в системе  почта {email}  пароль {password}")
    public void login(String email, String password) {
        driver.$(".title h3").shouldHave(text("Войдите в личный кабинет"));
        driver.$("#jivo-iframe-container").shouldBe(exist, Duration.ofSeconds(20));  //reset the form if being loaded after
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
        stepWithRole("Нажать кнопку Войти", () -> {
//                driver.$("button[type=submit]").click();  //Element not found {button[type=submit]}
            driver.$(".mb-2.btn.btn-primary.disable-outline")
                    .shouldHave(text("Далее"))
                    .click();
        });
    };
}


