package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

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
        pageTitleLocator = $(".page-content .h3.mb-40"),
        secondBankComisionsTextLocator = $$("div.logo-wrap__text").get(1),
        pagePaymentWizardTitleLocator = $("span.part"),
        payImgLocator = $("div.logo-wrap img"),
        docAgreementMaintenanceLocator = $("div .link-pdf");

    ElementsCollection
        paymentMethodsCollection = $$("div.logo-wrap"),
        bankComissionCollection = $$("div.logo-wrap__text");

    public SelectPaymentClientPage isOpened() {
        pageTitleLocator.shouldBe(visible);
        docAgreementMaintenanceLocator.shouldBe(visible);
        secondBankComisionsTextLocator.shouldBe(visible);
        // get text from second bank comission and sout it
        String secondBankComission = secondBankComisionsTextLocator.getText();
        System.out.println("secondBankComission = " + secondBankComission);
        payImgLocator.shouldBe(visible);
        return this;
    }

    public SelectPaymentClientPage payMIR() {
        paymentMethodsCollection.get(0).click();
        return this;
    }

    public SelectPaymentClientPage paySPB() {
        paymentMethodsCollection.get(1).click();
//        pagePaymentWizardTitleLocator.shouldBe(visible);
        return this;
    }




}
