package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PaymentWizardClientPage  extends BaseClientPage {

    public PaymentWizardClientPage(RoleBrowser browser) {
        super(browser);
    }
    private final String
        PAYMENT_WIZARD_TITLE ="QR", // "Система быстрых платежей",
        TEST_BANNER_WARNING = "Тестовый режим, реального перечисления средств не производится";


    SelenideElement
        pageTitleLocator = driver.$("div h3 span").as("Заголовок страницы Система быстрых платежей"),
        testModeWarningBannerLocator = driver.$("p.warning").as("Баннер Тестовый режим"),
        toPayTextLocator = driver.$("dl.field-totalAmount dd").as("Сумма к оплате"),
        cardAmountValueLocator = driver.$("div.cart_body .cart_amount_value").as(" Чек Сумма заказа"),
        cardTotalAmountValueLocator = driver.$("div.cart_body .total_sum").as("Чек Сумма к оплате"),
        payAgreementLinkLocator = driver.$("div.comment a[href='https://www.moneta.ru/info/public/users/nko/cardsserviceoffer.pdf']").as("Ссылка на договор оферты"),
        payButton = driver.$(".form_button.default").as("Кнопка Получить QR-код");


    public String formatPrice(String priceWithCommissions) {
        Double price = Double.parseDouble(priceWithCommissions);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setCurrencySymbol("₽");
        symbols.setGroupingSeparator(' ');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(price);
    }

    public void checkFinishLoading(String priceWithCommissions) {
        stepWithRole("Убедиться, что страница Система быстрых платежей загружена", () -> {
            pageTitleLocator.shouldHave(text(PAYMENT_WIZARD_TITLE)).shouldBe(visible, Duration.ofSeconds(60));
            // todo for now dont appear on dev            payAgreementLinkLocator.shouldBe(visible);
            stepWithRole("Убедиться, что отображается баннер Тестовый режим", () -> {
                testModeWarningBannerLocator.shouldHave(text(TEST_BANNER_WARNING)).shouldBe(visible);
            });
            String formattedPrice = formatPrice(priceWithCommissions);
            stepWithRole("Убедиться, что отображается сумма к оплате: " + formattedPrice, () -> {
                cardAmountValueLocator.shouldHave(text(formattedPrice));
                cardTotalAmountValueLocator.shouldHave(text(formattedPrice));
                toPayTextLocator.shouldHave(text(formattedPrice));
            });
            stepWithRole("Убедиться, что во всех формах одинаковая сумма к оплате" + formattedPrice, () -> {
                Assertions.assertThat(cardAmountValueLocator.getText()).isEqualTo(cardTotalAmountValueLocator.getText());
                Assertions.assertThat(toPayTextLocator.getText()).isEqualTo(cardTotalAmountValueLocator.getText());
                System.out.println("Сумма к оплате в Монете: " + toPayTextLocator.getText());
            });
            payButton.shouldBe(visible);
        });
    }


    public void payButton() {
        stepWithRole("Нажать кнопку Оплатить", () -> {
             payButton.click();
        });
    }


}
