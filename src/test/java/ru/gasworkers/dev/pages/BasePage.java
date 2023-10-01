package ru.gasworkers.dev.pages;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.PopUpNotificationsSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public abstract class BasePage extends BaseComponent {
    public final PopUpNotificationsSharedComponent popUp;
    public final UrlCheckerSharedComponent urlChecker;
    private final SelenideElement primaryButton = driver.$("[data-test-id=\"primary\"]").as("Основная кнопка");

    public BasePage(RoleBrowser browser) {
        super(browser);
        popUp = new PopUpNotificationsSharedComponent(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);
    }

    public void primaryButton() {
        step("Нажать на основную кнопку ", () -> {
            driver.$("[data-test-id='primary']").click();
        });
    }

    public void outlineButton() {
        step("Нажать на альтернативную кнопку", () -> {
            driver.$("[data-test-id='outline-primary']").click();
        });
    }

    public void waitForDocumentReady() {
        long startTime = System.currentTimeMillis();
        long timeout = 10000;  // 10 seconds timeout
        while (System.currentTimeMillis() - startTime < timeout) {
            if (Objects.equals(executeJavaScript("return document.readyState"), "complete")) {
                long loadTime = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Page load is complete in " + loadTime + " seconds");
                return;
            }
            try {
                Thread.sleep(100);  // Sleep for 100 milliseconds, then check again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Page did not load after 10 seconds");
    }


}