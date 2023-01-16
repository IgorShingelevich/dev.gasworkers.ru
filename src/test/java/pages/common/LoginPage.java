package pages.common;

import model.browser.RoleBrowser;
import pages.BasePage;

import static com.codeborne.selenide.Condition.*;

public final class LoginPage extends BasePage {

    public LoginPage(RoleBrowser browser) {
        super(browser);
    }

    public LoginPage open() {
        stepWithRole("Открыть страницу авторизации", () ->
                driver.open("/login"));
        return this;
    }

    public void login(String email, String password) {
        stepWithRole("Авторизоваться в системе", () -> {
            driver.$(".title h3").shouldHave(text("Войдите в личный кабинет"));
            driver.$("input[placeholder=E-mail]").setValue(email);
            driver.$("input[type=password]").setValue(password);
            driver.$(".login-form .btn-primary").click();
        });
    }

}
