package pages.profilePagesTODO.clientPages.cancelPage;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CancelMaintenancePage extends BaseClientPage {

    private final FocusHeaderComponent header;

    public CancelMaintenancePage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }

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
        yesButtonLocator.shouldBe(interactable).hover();
        yesButtonLocator.click();
        return this;
    }

    public CancelMaintenancePage noButton() {
        noButtonLocator.click();
        return this;
    }

}
