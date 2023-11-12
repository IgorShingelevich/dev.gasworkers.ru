package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.gasworkers.dev.extension.browser.Browser;

import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public final class DriverFactory {
    public static SelenideDriver getDriver(Browser annotation, ExtensionContext actualContext) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru");

        MutableCapabilities capabilities = BrowserCapabilities.CHROME_119.getCapabilities();

        config.browser(capabilities.getBrowserName());
        config.browserVersion(capabilities.getBrowserVersion());
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        config.timeout(8000);
        config.headless(false);
        config.holdBrowserOpen(false);
        config.browserCapabilities(capabilities);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        config.browserCapabilities(chromeOptions);

        if (isLocalExecution()) {
            configureLocalCapabilities(config, annotation, actualContext);
        } else {
            configureRemoteCapabilities(config, annotation, actualContext);
        }

        config.fileDownload(FOLDER);
        return new SelenideDriver(config);
    }

    private static boolean isLocalExecution() {
        String localExecution = System.getenv("LOCAL_EXECUTION");
        return localExecution != null && localExecution.equalsIgnoreCase("true");
    }

    private static void configureLocalCapabilities(SelenideConfig config, Browser annotation, ExtensionContext actualContext) {
        MutableCapabilities capabilities = config.browserCapabilities();

        MutableCapabilities selenoidCapabilities = new MutableCapabilities();
        selenoidCapabilities.setCapability("enableVNC", true);
        selenoidCapabilities.setCapability("enableVideo", false);

        String currentTestInstanceName = "Local: " + actualContext.getDisplayName();
        selenoidCapabilities.setCapability("name", currentTestInstanceName);

        capabilities.setCapability("selenoid:options", selenoidCapabilities);
        config.remote("http://localhost:4444/wd/hub");
    }

    private static void configureRemoteCapabilities(SelenideConfig config, Browser annotation, ExtensionContext actualContext) {
        MutableCapabilities capabilities = config.browserCapabilities();

        MutableCapabilities selenoidCapabilities = new MutableCapabilities();
        selenoidCapabilities.setCapability("enableVNC", true);
        selenoidCapabilities.setCapability("enableVideo", false);

        String currentRole = String.valueOf(annotation.role());
        String currentTestInstanceName = currentRole + ": " + actualContext.getDisplayName();
        System.out.println("browser instances : " + currentTestInstanceName);
        selenoidCapabilities.setCapability("name", currentTestInstanceName);
        selenoidCapabilities.setCapability("env", new ArrayList<String>() {{
            add("TZ=UTC");
        }});
        selenoidCapabilities.setCapability("labels", new HashMap<String, Object>() {{
            put("manual", "true");
        }});

        capabilities.setCapability("selenoid:options", selenoidCapabilities);
        config.remote("https://selenoid.tests.gasworkers.ru/wd/hub");
    }
}
