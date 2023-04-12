package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;

public abstract class BaseSidebarComponent extends BaseComponent {
    public final UrlCheckerSharedComponent urlChecker;

    public BaseSidebarComponent(RoleBrowser browser) {
        super(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);

    }
    SelenideElement
    supportServiceTitleLocator = driver.$(".support-service").as("Служба поддержки"),
    supportServicePhoneLocator = driver.$(".support-service__phone").as("8 800 302 89 04"),
    supportServiceEmailLocator = driver.$(".support-service .link-dark-blue").as("mail");

}
