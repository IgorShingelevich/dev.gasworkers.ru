package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectPaymentClientPage {

    public FocusHeaderComponent header = new FocusHeaderComponent();

    private final String
            SELECT_PAYMENT_TITLE = "Заключение договора ТО и оплата выезда мастера";

    SelenideElement
            pageTitleLocator = $(".page-content .h3.mb-40"),
            pagePaymentWizardTitleLocator = $("span.part"),

            docAgreementMaintenanceLocator = $("div .link-pdf");

    ElementsCollection
            paymentMethodsCollection = $$(".d-flex div.col");

    public SelectPaymentClientPage isOpened() {
        pageTitleLocator.shouldBe(visible);
        docAgreementMaintenanceLocator.shouldBe(visible);
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
