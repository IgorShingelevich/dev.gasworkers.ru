package pages.profilePages.clientPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SignSMSClientPage {
    public FocusHeaderComponent header = new FocusHeaderComponent();

    private final String
        SIGN_SMS_TITLE = "Подписание договора ТО ВДГО";




    SelenideElement
        pageSignSMSTitleLocator = $(".h3.mb-40"),
        smsCodeInput = $("input[name='smsCode']"),
        signButton = $("button.btn.btn-primary");

    ElementsCollection
        smsCodeInputCollection = $$(".code-input.mb-4 input");

    public SignSMSClientPage isOpened() {
        pageSignSMSTitleLocator.should(appear, Duration.ofSeconds(10));
        String titleSignSMSClientPage = pageSignSMSTitleLocator.getText();
        System.out.println("titleSignSMSClientPage: " + titleSignSMSClientPage);
    return this;
    }

    public SignSMSClientPage inputSMSCode(String smsCode) {
        smsCodeInputCollection.get(0).setValue(smsCode.substring(0,1));
        smsCodeInputCollection.get(1).setValue(smsCode.substring(1,2));
        smsCodeInputCollection.get(2).setValue(smsCode.substring(2,3));
        smsCodeInputCollection.get(3).setValue(smsCode.substring(3,4));
        smsCodeInputCollection.get(4).setValue(smsCode.substring(4,5));
        smsCodeInputCollection.get(5).setValue(smsCode.substring(5,6));
        return this;
    }

    public SignSMSClientPage sign() {
        signButton.click();
        return this;
    }




}
