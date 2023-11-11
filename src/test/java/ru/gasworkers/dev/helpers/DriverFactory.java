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
        config.holdBrowserOpen(false); // check remote
        config.browserCapabilities(capabilities);
        ChromeOptions chromeOptions = new ChromeOptions();
        // Install and activate the extension
//        chromeOptions.addExtensions(new File("src/test/resources/web/eimadpbcbfnmbkopoojfekhnkhdbieeh-4.9.64-Crx4Chrome.com.crx"));
        // Enable camera and microphone access
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
//        chromeOptions.addArguments("auto-open-devtools-for-tabs"); // open dev tools

        config.browserCapabilities(chromeOptions);

        if (isLocalExecution()) {
            // Handle local execution settings if needed
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
        config.remote("http://tests.gasworkers.ru:4444/wd/hub");
    }
}
