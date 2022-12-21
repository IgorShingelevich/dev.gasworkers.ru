package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final String LOGIN_PAGE_TITLE_TEXT = "Войдите в личный кабинет";

    /*<h3 data-v-3e43ab48="">Войдите в личный кабинет</h3>*/
    SelenideElement loginPageTitleLocator = $(By.xpath("//h3[@data-v-3e43ab48='']"));


/*<input data-v-9be155e4="" id="94" placeholder="E-mail" type="text">*/
    SelenideElement emailFieldLocator = $(By.xpath("//input[@placeholder='E-mail']"));
    /*<input data-v-9be155e4="" id="95" placeholder="Пароль" type="password">*/
    SelenideElement passwordFieldLocator = $(By.xpath("//input[@placeholder='Пароль']"));
    /*<button data-v-6d08f792="" data-v-3e43ab48="" class="btn btn-primary disable-outline">
  Далее
</button>*/
    SelenideElement loginButtonLocator = $(By.xpath("//button[@class='btn btn-primary disable-outline']"));

public void open() {
        Selenide.open("/login");
    }

    public void login(String emailClient, String passwordClient) {
        loginPageTitleLocator.shouldHave(text(LOGIN_PAGE_TITLE_TEXT));
        emailFieldLocator.setValue(emailClient);
        passwordFieldLocator.setValue(passwordClient);
        loginButtonLocator.click();
    }

}
