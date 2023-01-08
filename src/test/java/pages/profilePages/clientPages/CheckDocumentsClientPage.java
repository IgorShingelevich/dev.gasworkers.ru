package pages.profilePages.clientPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CheckDocumentsClientPage {
    public FocusHeaderComponent header = new FocusHeaderComponent();

    private final String
            CHECK_DOCUMENTS_TITLE = "Для заключения договора ТО заполните недостающие данные";

    SelenideElement
            pageTitleLocator = $(".h3.mb-32"),
            pageTitlePaymentPageLocator = $(".page-content .h3.mb-40"),
            pageSubtitleLocator = $(".h4.mb-32"),
            inputPassportIssuedByFieldLocator = $("input[placeholder='Кем выдан']"),
            makeContractButton = $("button.btn.btn-primary");

    public CheckDocumentsClientPage isOpened() {
        pageTitleLocator.shouldHave(text(CHECK_DOCUMENTS_TITLE));
        return this;
    }

    public CheckDocumentsClientPage makeContract() {
        inputPassportIssuedByFieldLocator.shouldNot(empty);
        makeContractButton.click();
        //wait for spinner to disappear
        pageTitlePaymentPageLocator.shouldHave(text("Заключение договора ТО и оплата выезда мастера"));
        pageTitlePaymentPageLocator.shouldBe(visible);
        return this;
    }






}

