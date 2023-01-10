package pages.profilePages.clientPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import java.time.Duration;

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
        makeContractButton = $("button.btn.btn-primary"),
        spinerLoaderLocator = $("div.gas-loader");
        // <div data-v-eb791b80="" class="gas-loader"></div>


    public CheckDocumentsClientPage isOpened() {
        pageTitleLocator.shouldHave(text(CHECK_DOCUMENTS_TITLE));
        String CheckDocumentsClientPageTitle = pageTitleLocator.getText();
        System.out.println("CheckDocumentsClientPageTitle: " + CheckDocumentsClientPageTitle);
        return this;
    }

    public CheckDocumentsClientPage makeContract()  {
        inputPassportIssuedByFieldLocator.shouldNot(empty);
       makeContractButton.click();
        spinerLoaderLocator.should(disappear);
        pageTitlePaymentPageLocator.should(appear, Duration.ofSeconds(10));


        return this;
    }






}

