package pages.common;

import com.codeborne.selenide.SelenideElement;
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
//            driver.$(".iconWrap_ea30").shouldBe(visible, Duration.ofSeconds(20));  //reset the form if being loaded after
//            driver.$("input[type=password]").pressTab();
//            driver.$(".login-form .btn-primary").pressEnter();
            driver.$(".login-form .btn-primary").click();
        });
    }

}
