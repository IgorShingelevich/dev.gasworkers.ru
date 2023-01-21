package pages.client.repair;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SelectDateRepairPage extends BaseClientPage {

    private final FocusHeaderComponent header;
    public SelectDateRepairPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }


    private final String
            SELECT_DATE_REPAIR_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";


    SelenideElement
            pageTitleLocator = $(".col-12.col-md-6 .text-center");


    public SelectDateRepairPage isOpened() {
        pageTitleLocator.shouldHave(text(SELECT_DATE_REPAIR_TITLE));
        return this;
    }
}
