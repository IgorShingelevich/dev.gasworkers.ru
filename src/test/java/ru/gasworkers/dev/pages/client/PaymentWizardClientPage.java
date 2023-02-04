package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.time.Duration;

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
        pageTitleLocator = driver.$("span.part"),
        pageSignSMSTitleLocator = driver.$(".h3.mb-40"),
        getQRCode = driver.$(".form_button.default");


    public PaymentWizardClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Система быстрых платежей загружена", () -> {
            pageTitleLocator.shouldHave(text(PAYMENT_WIZARD_TITLE)).shouldBe(visible, Duration.ofSeconds(20));
            String pageWizardTitle = pageTitleLocator.getText();
            getQRCode.shouldBe(visible);
        });
        return this;
    }

    public PaymentWizardClientPage getQRCode() {
        stepWithRole("Нажать кнопку Получить QR-код", () -> {
            getQRCode.click();
            pageSignSMSTitleLocator.shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }


}
