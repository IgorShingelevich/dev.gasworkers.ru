package pages.client.maintenance;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SelectInsuranceClientPage extends BaseClientPage {
    private final FocusHeaderComponent header;
    public  SelectInsuranceClientPage (RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }
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
