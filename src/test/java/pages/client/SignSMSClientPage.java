package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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
            pageSignSMSTitleLocator = driver.$(".h3.mb-40"),
            smsCodeInput = driver.$("input[name='smsCode']"),
            signButton = driver.$("button.btn.btn-primary");

    ElementsCollection smsCodeInputCollection = driver.$$(".code-input.mb-4 input");

    public SignSMSClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Подписание СМС загружена", () -> {
            pageSignSMSTitleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SIGN_SMS_TITLE));
        });
        return this;
    }

    public SignSMSClientPage inputSMSCode(String smsCode) {
        stepWithRole("Ввести СМС код: " + smsCode, () -> {
            smsCodeInputCollection.get(0).setValue(smsCode);
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
