package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.profilePages.clientPages.HomeClientPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {


    HomeClientPage homeClientPage = new HomeClientPage();


    private final String LOGIN_PAGE_TITLE_TEXT = "Войдите в личный кабинет";

    /*<h3 data-v-3e43ab48="">Войдите в личный кабинет</h3>*/
    SelenideElement
        loginPageTitleLocator = $(".login-form>.title>h3"),


/*<input data-v-9be155e4="" id="94" placeholder="E-mail" type="text">*/
     emailFieldLocator = $(".gas-input input[placeholder=E-mail]"),
        //$(By.xpath("//div[@class='login-form'] //input[@placeholder='E-mail']"));
    /*<input data-v-9be155e4="" id="95" placeholder="Пароль" type="password">*/
     passwordFieldLocator = $(By.xpath("//div[@class='login-form'] //input[@placeholder='Пароль']")),
    /*<button data-v-6d08f792="" data-v-3e43ab48="" class="btn btn-primary disable-outline">
  Далее
</button>*/
     loginButtonLocator = $(By.xpath("//button[@class='btn btn-primary disable-outline']"));
        //$(".form-section center .btn btn-primary disable-outline"); // this is wrong
        //$(By.xpath("//div[@class='login-form'] //button[@class='btn btn-primary disable-outline']")); // Other element would receive the click: <div class="form-section center"

public void open() {
        Selenide.open("/login");
    }

    public void login(String email, String password) {
        loginPageTitleLocator.shouldHave(text(LOGIN_PAGE_TITLE_TEXT)).shouldBe(visible);
        emailFieldLocator.shouldBe(interactable);
        emailFieldLocator.shouldBe(visible);
        emailFieldLocator.click();
        emailFieldLocator.pressEnter();
        emailFieldLocator.sendKeys(email);
        emailFieldLocator.clear();
        emailFieldLocator.setValue(email);
        emailFieldLocator.pressEnter();
        emailFieldLocator.shouldHave(value(email));
        passwordFieldLocator.shouldBe(interactable);
        passwordFieldLocator.shouldBe(editable);
        passwordFieldLocator.click();
        passwordFieldLocator.setValue(password);
        passwordFieldLocator.pressEnter();

        loginButtonLocator.shouldNotBe(disabled);
        loginButtonLocator.click();
//        homeClientPage.isOpened();


    }

}
