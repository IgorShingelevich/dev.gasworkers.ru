package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.MutableCapabilities;
import ru.gasworkers.dev.extension.browser.Browser;

import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public final class DriverFactory {
    public static SelenideDriver getDriver(Browser annotation, ExtensionContext actualContext) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru");

        MutableCapabilities capabilities = BrowserCapabilities.CHROME_115.getCapabilities();

        config.browser(capabilities.getBrowserName());
        config.browserVersion(capabilities.getBrowserVersion());
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        config.timeout(8000);
        config.headless(false);
        config.holdBrowserOpen(false); // check remote
        config.browserCapabilities(capabilities);

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
        selenoidCapabilities.setCapability("enableVideo", true);

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

/*private static final Object driverCreationLock = new Object();

    public static SelenideDriver getDriver(Browser annotation, ExtensionContext actualContext) throws MalformedURLException {
        List<SelenideDriver> drivers = new ArrayList<>();

        for (BrowserCapabilities browserCapability : BrowserCapabilities.values()) {
            drivers.add(createDriver(browserCapability, annotation, actualContext));
        }

        // You can now use the drivers list if needed (e.g., for cleanup or assertions).
        // For this example, we'll return the last driver created (you can modify this as per your requirements).
        return drivers.get(drivers.size() - 1);
    }

    private static SelenideDriver createDriver(BrowserCapabilities browserCapability, Browser annotation, ExtensionContext actualContext) throws MalformedURLException {
        synchronized (driverCreationLock) {
            SelenideConfig config = new SelenideConfig();
            config.baseUrl("https://dev.gasworkers.ru");
            config.browserSize(annotation.browserSize());
            config.browserPosition(annotation.browserPosition());
            config.reportsFolder("target/selenide");
            config.timeout(8000);
            config.headless(false);
            config.holdBrowserOpen(false);

            MutableCapabilities capabilities = browserCapability.getCapabilities();

            config.browserCapabilities(capabilities);
            config.fileDownload(FOLDER);

            return new SelenideDriver(config);
        }
    }*/



/*SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru"); // ToDo вынести в конфиг
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        config.reportsFolder("target/selenide");
        config.timeout(8000);
        config.headless(false);
//         С удаленным запуском нельзя ставить в true!
        config.holdBrowserOpen(false);
// Enable camera and microphone access
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        config.browserCapabilities(chromeOptions);
        config.fileDownload(FOLDER);
        config.browser("chrome");
        config.browserVersion("115.0");
        config.remote("http://localhost:4444/wd/hub");
//         http://127.0.0.1:4444/wd/hub

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        config.browserCapabilities(capabilities);
        config.fileDownload(FOLDER);
        return new SelenideDriver(config);*/


/* step("Запускаем тесты удаленно", () -> {
            config.remote("http://5.161.120.34:4444/wd/hub");
            config.browser("chrome");
            config.browserVersion("107.0");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            config.browserCapabilities(capabilities);
        });*/

// Install and activate the extension
//        chromeOptions.addExtensions(new File("src/test/resources/web/eimadpbcbfnmbkopoojfekhnkhdbieeh-4.9.64-Crx4Chrome.com.crx"));