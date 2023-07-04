package ru.gasworkers.dev.pages;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.PopUpNotificationsSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;

import static io.qameta.allure.Allure.step;

public abstract class BasePage extends BaseComponent {
    public final PopUpNotificationsSharedComponent popUp;
    public final UrlCheckerSharedComponent urlChecker;
    public SelenideElement primaryButton = driver.$("[data-test-id=\"primary\"]").as("Основная кнопка");

    public BasePage(RoleBrowser browser) {
        super(browser);
        popUp = new PopUpNotificationsSharedComponent(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);
    }

    public void pressPrimaryButton() {
        step("Нажать на основную кнопку ", () -> {
            driver.$("[data-test-id='primary']").click();
        });
    }

    public void pressOutlineButton() {
        step("Нажать на альтернативную кнопку", () -> {
            driver.$("[data-test-id='outline-primary']").click();
        });
    }
}