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
        pageTitleLocator = driver.$("div h3 span").as("Заголовок страницы Система быстрых платежей"),
        totalAmountLocator = driver.$("dl.field-totalAmount").as("Сумма к оплате"),
        getQRCode = driver.$(".form_button.default").as("Кнопка Получить QR-код");


    public PaymentWizardClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Система быстрых платежей загружена", () -> {
            pageTitleLocator.shouldHave(text(PAYMENT_WIZARD_TITLE)).shouldBe(visible, Duration.ofSeconds(60));
            String pageWizardTitle = pageTitleLocator.getText();
            getQRCode.shouldBe(visible);
        });
        return this;
    }

    public PaymentWizardClientPage getQRCode() {
        stepWithRole("Нажать кнопку Получить QR-код", () -> {
            totalAmountLocator.shouldBe(visible, Duration.ofSeconds(20));
            getQRCode.click();
        });
        return this;
    }


}
