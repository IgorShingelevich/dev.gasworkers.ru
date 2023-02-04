package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SignSMSClientPage extends BaseClientPage {
    private final FocusHeaderComponent header;

    public SignSMSClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }

    private final String
            SIGN_SMS_TITLE = "Подписание договора ТО ВДГО";

    SelenideElement
            pageSignSMSTitleLocator = driver.$(".h3.mb-40").as("Заголовок страницы Подписание СМС"),
            signButton = driver.$("button.btn.btn-primary").as("Кнопка Подписать");

    ElementsCollection smsCodeInputCollection = driver.$$("div.code-input input").as("Коллекция полей ввода СМС кода");

    public SignSMSClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Подписание СМС загружена", () -> {
            String currentUrl = driver.url();
            System.out.println("currentUrl = " + currentUrl);
            pageSignSMSTitleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SIGN_SMS_TITLE));
        });
        return this;
    }

    public SignSMSClientPage inputSMSCode(String smsCode) {
        stepWithRole("Ввести СМС код: " + smsCode, () -> {
            smsCodeInputCollection.get(0).setValue(smsCode);
            System.out.println("smsCode: " + smsCode);
        });
        return null;
    }

    public SignSMSClientPage sign() {
        stepWithRole("Нажать кнопку Подписать", () -> {
            signButton.click();
        });
        return this;
    }
}
