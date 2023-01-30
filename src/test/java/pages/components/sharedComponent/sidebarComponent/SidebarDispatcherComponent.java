package pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

public class SidebarDispatcherComponent extends BaseComponent {

    public SidebarDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement generalMapLocator = driver.$$(".sidebar .link").get(0),
            allMastersListLinkLocator = driver.$$(".sidebar .link").get(1),
            profileDispatcherLinkLocator = driver.$$(".sidebar .link").get(2),
            supportServiceTitleLocator = driver.$(".support-service"),
            supportServicePhoneLocator = driver.$(".support-service__phone"),
            supportServiceEmailLocator = driver.$(".support-service .link-dark-blue");


    public SidebarDispatcherComponent generalMap() {
        stepWithRole("Переход на Крату Диспетчера", () -> {
            generalMapLocator.click();
        });
        return this;
    }

    public SidebarDispatcherComponent allMasters() {
        stepWithRole("Переход в раздел Список Мастеров", () -> {
            allMastersListLinkLocator.click();
        });
        return this;
    }

    public SidebarDispatcherComponent profile() {
        stepWithRole("Переход в раздел Профиль", () -> {
            profileDispatcherLinkLocator.click();
        });
        return this;
    }

}

// TODO arhive Orders   //check that the order with this number is not visible in the list of orderNumberLinkCollection