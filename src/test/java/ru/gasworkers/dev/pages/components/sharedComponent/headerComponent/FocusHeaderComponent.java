package ru.gasworkers.dev.pages.components.sharedComponent.headerComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FocusHeaderComponent extends BaseComponent {

    public FocusHeaderComponent (RoleBrowser browser) {
        super(browser);
    }



    SelenideElement
        logoLocator = $("#gas__header .logo"),
        linkSupportLocator = $x("//a[contains(text(), 'Служба поддержки')]"),
        linkExitLocator = $x("//a[contains(text(), 'Выйти')]"),
        approveCancelPageTitleLocator = $("div.recovery-box");

    ElementsCollection focusHeaderNavButtonsCollection = $$(".d-flex.justify-content-md-end.mb-4 button.link-dark-blue");


    public FocusHeaderComponent back() {
        focusHeaderNavButtonsCollection.first().click();
        return this;
    }

    public FocusHeaderComponent cancelOrder() {
        focusHeaderNavButtonsCollection.get(1).click();
        approveCancelPageTitleLocator.shouldBe(visible);
        return this;
    }

public FocusHeaderComponent logo() {
    logoLocator.shouldBe(visible).click();
    return this;
}

    public FocusHeaderComponent signOut() {
        linkExitLocator.shouldBe(visible).click();
        return this;
    }

    public FocusHeaderComponent support() {
        linkSupportLocator.shouldBe(visible).click();
        return this;
    }



}