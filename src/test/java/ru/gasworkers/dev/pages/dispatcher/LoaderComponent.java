package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class LoaderComponent extends BaseComponent {
    private final SelenideElement loaderMap = driver.$("img[src*='logo-blue-loader.svg']"),
            loaderPage = driver.$("div.gas-loader");

    public LoaderComponent(RoleBrowser browser) {
        super(browser);
    }

    public void handleLoaderMap() {
        if (loaderMap.isDisplayed()) {
            long startTime = System.currentTimeMillis();
            try {
                loaderMap.shouldNotBe(visible, Duration.ofSeconds(10));
                long endTime = System.currentTimeMillis();
                long durationInSeconds = (endTime - startTime) / 1000;
                System.out.println("Waited for loader for " + durationInSeconds + " seconds.");
            } catch (Exception e) {
                System.out.println("10 seconds of loader exceeded.");
                throw e; // re-throw the exception to ensure the test fails
            }
        }
    }

    public void handleLoaderPage() {
        if (loaderPage.isDisplayed()) {
            long startTime = System.currentTimeMillis();
            try {
                loaderPage.shouldNotBe(visible, Duration.ofSeconds(10));
                long endTime = System.currentTimeMillis();
                long durationInSeconds = (endTime - startTime) / 1000;
                System.out.println("Waited for loader for " + durationInSeconds + " seconds.");
            } catch (Exception e) {
                System.out.println("10 seconds of loader exceeded.");
                throw e; // re-throw the exception to ensure the test fails
            }
        }
    }
}
