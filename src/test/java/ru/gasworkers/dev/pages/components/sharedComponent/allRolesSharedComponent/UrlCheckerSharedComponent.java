package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.Screenshots;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.io.File;
import java.time.Duration;

public class UrlCheckerSharedComponent  extends BaseComponent {
    public UrlCheckerSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    public void urlStartsWith(String url) {
        stepWithRole("Проверить URL страницы: " + url, () -> {
            try {
                driver.Wait().withTimeout(Duration.ofSeconds(20)).until(webDriver -> webDriver.getCurrentUrl().startsWith(url));
            } catch (Exception e) {
                String actualUrl = driver.url();
                throw new RuntimeException("URL check fail. Url should be " + url + " but actual url is - " + actualUrl , e);
            }
        });
    }


    public void urlStartsWith2(String url) {
        stepWithRole("Проверить URL страницы: " + url, () -> {
            try {
                driver.Wait().withTimeout(Duration.ofSeconds(20)).until(webDriver -> webDriver.getCurrentUrl().startsWith(url));
            } catch (Exception e) {
                String actualUrl = driver.url();
                throw new RuntimeException("URL check fail. Url should be " + url + " but actual url is - " + actualUrl, e);
                // screenshot here
            }
        });
    }

    public void urlStartsWith(String url, int timeout) {
        stepWithRole("Проверить URL страницы: " + url, () -> {
            try {
                driver.Wait().withTimeout(Duration.ofSeconds(timeout)).until(webDriver -> webDriver.getCurrentUrl().startsWith(url));
            } catch (Exception e) {
                String actualUrl = driver.url();
                throw new RuntimeException("URL check fail. Url should be " + url + " but actual url is - " + actualUrl, e);
            }
        });
    }

    public void urlContains(String url) {
        stepWithRole("Проверить URL страницы: " + url, () -> {
            try {
                driver.Wait().withTimeout(Duration.ofSeconds(20)).until(webDriver -> webDriver.getCurrentUrl().contains(url));
            } catch (Exception e) {
                String actualUrl = driver.url();
                throw new RuntimeException("URL check fail. Url should be " + url + " but actual url is - " + actualUrl, e);
            }
        });
    }

    public void urlContains(String url, int timeout) {
        stepWithRole("Проверить URL страницы: " + url, () -> {
            try {
                driver.Wait().withTimeout(Duration.ofSeconds(timeout)).until(webDriver -> webDriver.getCurrentUrl().contains(url));
            } catch (Exception e) {
                String actualUrl = driver.url();
                throw new RuntimeException("URL check fail. Url should be " + url + " but actual url is - " + actualUrl, e);
            }
        });
    }
}
