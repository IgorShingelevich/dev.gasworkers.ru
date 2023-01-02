package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.profilePages.clientPages.ClientProfilePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    /**
     * <div class="login-form" data-v-e4e033d2=""><div class="title" data-v-e4e033d2=""><h3 data-v-e4e033d2="">Войдите в личный кабинет</h3></div> <div class="gas-input" data-v-9be155e4="" data-v-e4e033d2=""><!----> <div data-v-9be155e4=""><input id="16" placeholder="Телефон" type="text" data-v-9be155e4="" disabled="disabled"> <!----></div> <!----></div> <div class="divider" data-v-e4e033d2="">Введите телефон или Email</div> <div class="gas-input" data-v-9be155e4="" data-v-e4e033d2=""><!----> <div data-v-9be155e4=""><input id="17" placeholder="E-mail" type="text" data-v-9be155e4=""> <!----></div> <!----></div> <div class="gas-input mb-2" data-v-9be155e4="" data-v-e4e033d2=""><!----> <div class="password-type" data-v-9be155e4=""><input id="18" placeholder="Пароль" type="password" data-v-9be155e4=""> <div class="eye-icon" data-v-9be155e4=""></div> <!----></div> <!----></div> <div class="form-section" data-v-e4e033d2=""><a href="/recovery" class="small" data-v-e4e033d2="">
     *         Восстановить пароль
     *       </a></div> <div class="form-section center" data-v-e4e033d2=""><button class="btn btn-primary disable-outline" data-v-6d08f792="" data-v-e4e033d2="">
     *   Далее
     * </button></div></div>*/


    ClientProfilePage clientProfilePage = new ClientProfilePage();


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

    public void login(String emailClient, String passwordClient) {
        loginPageTitleLocator.shouldHave(text(LOGIN_PAGE_TITLE_TEXT)).shouldBe(visible);
        emailFieldLocator.shouldBe(interactable);
        emailFieldLocator.shouldBe(visible);
        emailFieldLocator.click();
        emailFieldLocator.clear();
        emailFieldLocator.sendKeys(emailClient);
//        emailFieldLocator.setValue(emailClient);
        emailFieldLocator.pressEnter();
        passwordFieldLocator.shouldBe(interactable);
        passwordFieldLocator.shouldBe(editable);
        passwordFieldLocator.click();
        passwordFieldLocator.setValue(passwordClient);
        passwordFieldLocator.pressEnter();

        loginButtonLocator.shouldNotBe(disabled);
        loginButtonLocator.click();
        clientProfilePage.isOpened();


    }

}
