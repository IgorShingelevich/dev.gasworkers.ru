package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.profilePages.clientPages.HomeClientPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {




    private final String
        LOGIN_PAGE_TITLE_TEXT = "Войдите в личный кабинет";

    SelenideElement
        loginPageTitleLocator = $(".login-form>.title>h3"),
        emailFieldLocator = $(".gas-input input[placeholder=E-mail]"),
        passwordFieldLocator = $("div.password-type input"),
                // $(By.xpath("//div[@class='login-form'] //input[@placeholder='Пароль']")),

        loginButtonLocator = $(".mb-2.btn.btn-primary");

    ElementsCollection
        inputFieldsCollection = $$("div.gas-input input");

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
