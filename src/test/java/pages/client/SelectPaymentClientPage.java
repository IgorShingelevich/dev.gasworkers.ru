package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectPaymentClientPage  extends BaseClientPage {

    private final FocusHeaderComponent header;
    public SelectPaymentClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }
    private final String
            SELECT_PAYMENT_TITLE = "Заключение договора ТО и оплата выезда мастера";

    SelenideElement
        pageTitleLocator = driver.$(".page-content .h3.mb-40"),
        secondBankComissionsTextLocator = driver.$$("div.logo-wrap__text").get(1),
        pagePaymentWizardTitleLocator = driver.$("span.part"),
        payImgLocator = driver.$("div.logo-wrap img"),
        docAgreementMaintenanceLocator = driver.$("div .link-pdf");

    ElementsCollection
        paymentMethodsCollection = driver.$$("div.logo-wrap"),
        bankComissionCollection = driver.$$("div.logo-wrap__text");

    public SelectPaymentClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Выбор Способа Оплаты загружена", () -> {
            pageTitleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SELECT_PAYMENT_TITLE));
            docAgreementMaintenanceLocator.shouldBe(visible);
            secondBankComissionsTextLocator.shouldBe(visible);
            // get text from second bank comission and sout it
            String secondBankComission = secondBankComissionsTextLocator.getText();
            System.out.println("secondBankComission = " + secondBankComission);
            payImgLocator.shouldBe(visible, Duration.ofSeconds(20));
        });

        return this;
    }

    public SelectPaymentClientPage payMIR() {
        paymentMethodsCollection.get(0).click();
        return this;
    }

    public SelectPaymentClientPage paySPB() {
        stepWithRole("Нажать кнопку Оплатить через SPB", () -> {
            paymentMethodsCollection.get(1).click();
        });
//        pagePaymentWizardTitleLocator.shouldBe(visible);
        return this;
    }
}
