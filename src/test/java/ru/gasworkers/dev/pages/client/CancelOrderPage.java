package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CancelOrderPage extends BaseClientPage {

    private final FocusHeaderComponent header;

    public CancelOrderPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }

    private final String
        PAGE_TITLE = "Отмена заказа";

    SelenideElement
        pageTitleLocator = driver.$(".recovery-box h3"),
        yesButtonLocator = driver.$(".global-btn-wrapper.justify-content-center .btn.btn-outline-primary.disable-outline"),
        noButtonLocator = driver.$(".global-btn-wrapper.justify-content-center .btn.btn-primary.disable-outline");


    public CancelOrderPage checkFinishLoading() {
        pageTitleLocator.shouldHave(text(PAGE_TITLE));
        return this;
    }

    public CancelOrderPage yesButton() {
        yesButtonLocator.shouldBe(interactable).hover();
        yesButtonLocator.click();
        return this;
    }

    public CancelOrderPage backButton() {
        noButtonLocator.click();
        return this;
    }

}
