package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SelectDateRepairPage {

    public FocusHeaderComponent header = new FocusHeaderComponent();


    private final String
            SELECT_DATE_REPAIR_TITLE = "Выберите желаемую дату (диапазон дат) и время приезда мастера";


    SelenideElement
            pageTitleLocator = $(".col-12.col-md-6 .text-center");


    public SelectDateRepairPage isOpened() {
        pageTitleLocator.shouldHave(text(SELECT_DATE_REPAIR_TITLE));
        return this;
    }
}
