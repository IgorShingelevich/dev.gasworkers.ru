package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class SidebarDispatcherComponent extends BaseSidebarComponent {

    public SidebarDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement generalMapLocator = driver.$$(".sidebar .link").get(0).as("Карта Диспетчера"),
            allMastersListLinkLocator = driver.$$(".sidebar .link").get(1).as("Список Мастеров"),
            profileDispatcherLinkLocator = driver.$$(".sidebar .link").get(2).as("Профиль");



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