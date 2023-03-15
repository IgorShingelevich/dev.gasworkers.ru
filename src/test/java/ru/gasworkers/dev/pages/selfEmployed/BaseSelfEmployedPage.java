package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;

public abstract class BaseSelfEmployedPage extends BasePage {
    public final ModeSwitcherSelfEmployedComponent mode;

    public BaseSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        mode = new ModeSwitcherSelfEmployedComponent(browser);
    }





}
