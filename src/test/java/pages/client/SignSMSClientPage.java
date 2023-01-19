package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    ElementsCollection
        smsCodeInputCollection = driver.$$(".code-input.mb-4 input");

    public SignSMSClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Подписание СМС загружена", () -> {
            pageSignSMSTitleLocator.should(appear, Duration.ofSeconds(20)).shouldHave(text(SIGN_SMS_TITLE));
            String titleSignSMSClientPage = pageSignSMSTitleLocator.getText();
            System.out.println("titleSignSMSClientPage: " + titleSignSMSClientPage);
        });
    return this;
    }

    public SignSMSClientPage inputSMSCode(Integer smsCode) {
        stepWithRole("Ввести СМС код: " + smsCode, () -> {
            smsCodeInputCollection.get(0).setValue(smsCode.toString().substring(0, 1));
            smsCodeInputCollection.get(1).setValue(smsCode.toString().substring(1,2));
            smsCodeInputCollection.get(2).setValue(smsCode.toString().substring(2,3));
            smsCodeInputCollection.get(3).setValue(smsCode.toString().substring(3,4));
            smsCodeInputCollection.get(4).setValue(smsCode.toString().substring(4,5));
            smsCodeInputCollection.get(5).setValue(smsCode.toString().substring(5,6));
        });
        System.out.println("FirstClientSmsCode: " + smsCode);
        return this;
    }

    public SignSMSClientPage sign() {
        stepWithRole("Нажать кнопку Подписать", () -> {
            signButton.click();
        });
        return this;
    }




}
