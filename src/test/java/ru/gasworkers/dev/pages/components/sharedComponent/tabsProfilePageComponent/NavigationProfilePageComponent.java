package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class NavigationProfilePageComponent extends BaseComponent {
    public NavigationProfilePageComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
    profileTabText = "Профиль",
            commonTabText = "Общие данные",
    contactsTabText = "Контактные данные",
            equipmentTabText = "Оборудование",
    passwordTabText = "Пароль",
            notificationsTabText = "Уведомления";


    ElementsCollection
            navigationTabsCollection = driver.$$("div.navigation-block ul li").as("Вкладки навигации");

    public void profile() {
        stepWithRole("Перейти на вкладку профиль", () -> {
            navigationTabsCollection.get(0).shouldHave(text(profileTabText)).click();
        });
    }

    public void common() {
        stepWithRole("Перейти на вкладку общее данные", () -> {
            navigationTabsCollection.get(1).shouldHave(text(commonTabText)).click();
        });
    }

    public void contacts() {
        stepWithRole("Перейти на вкладку контактные данные", () -> {
            navigationTabsCollection.get(2).shouldHave(text(contactsTabText)).click();
        });
    }

    public void equipment() {
        stepWithRole("Перейти на вкладку оборудование", () -> {
            navigationTabsCollection.get(3).shouldHave(text(equipmentTabText)).click();
        });
    }

    public void password() {
        stepWithRole("Перейти на вкладку пароль", () -> {
            navigationTabsCollection.get(4).shouldHave(text(passwordTabText)).click();
        });
    }

    public void notifications() {
        stepWithRole("Перейти на вкладку уведомления", () -> {
            navigationTabsCollection.get(5).shouldHave(text(notificationsTabText)).click();
        });
    }


}
