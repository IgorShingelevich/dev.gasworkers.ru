package pages.client;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PaymentWizardClientPage  extends BaseClientPage {

    public PaymentWizardClientPage(RoleBrowser browser) {
        super(browser);
    }
    private final String
        PAYMENT_WIZARD_TITLE = "Система быстрых платежей";

    SelenideElement
        pageTitleLocator = $("span.part"),
        pageSignSMSTitleLocator = $(".h3.mb-40"),
        getQRCode = $(".form_button.default");


    public PaymentWizardClientPage isOpened() {
        pageTitleLocator.shouldHave(text(PAYMENT_WIZARD_TITLE));
        String pageWizardTitle = pageTitleLocator.getText();
        System.out.println("pageWizardTitle = " + pageWizardTitle);
        return this;
    }

    public PaymentWizardClientPage getQRCode() {
        getQRCode.click();
        pageSignSMSTitleLocator.shouldBe(visible);
        return this;
    }


}
