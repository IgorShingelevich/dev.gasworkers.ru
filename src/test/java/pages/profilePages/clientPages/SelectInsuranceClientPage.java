package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SelectInsuranceClientPage {
   public FocusHeaderComponent header = new FocusHeaderComponent();

    private final String
            SELECT_INSURANCE_TITLE = "Выберите договор ТО";

    SelenideElement
        pageTitleLocator = $(".h2.text-center.mb-4"),
        nextButton = $(".gas-box .btn.btn-primary");

    public SelectInsuranceClientPage isOpened() {
        pageTitleLocator.shouldHave(text(SELECT_INSURANCE_TITLE));
        return this;
    }

    public SelectInsuranceClientPage next() {
        nextButton.click();
        return this;
    }

}
