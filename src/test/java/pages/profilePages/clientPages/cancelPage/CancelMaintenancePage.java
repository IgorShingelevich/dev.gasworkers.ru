package pages.profilePages.clientPages.cancelPage;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CancelMaintenancePage {

    public FocusHeaderComponent header = new FocusHeaderComponent();

    private final String
        CANCEL_MAINTENANCE_TITLE = "Отмена заказа";

    SelenideElement
        cancelPageTitleLocator = $(".recovery-box h3"),
        yesButtonLocator = $(".global-btn-wrapper.justify-content-center .btn.btn-outline-primary.disable-outline"),
        noButtonLocator = $(".global-btn-wrapper.justify-content-center .btn.btn-primary.disable-outline");


    public CancelMaintenancePage isOpened() {
        cancelPageTitleLocator.shouldHave(text(CANCEL_MAINTENANCE_TITLE));
        return this;
    }

    public CancelMaintenancePage yesButton() {
        yesButtonLocator.click();
        return this;
    }

    public CancelMaintenancePage noButton() {
        noButtonLocator.click();
        return this;
    }

}
