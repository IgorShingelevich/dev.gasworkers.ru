package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.gasworkers.dev.extension.browser.Browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) throws MalformedURLException {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru");
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        config.reportsFolder("target/selenide");
        config.timeout(8000);
        config.headless(false);
        config.holdBrowserOpen(false);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");

        config.browserCapabilities(chromeOptions);
        config.fileDownload(FOLDER);
        config.browser("chrome");
        config.browserVersion("115.0");
        config.remote("http://localhost:4444/wd/hub");

        String videoOutputDir = "/opt/selenoid/video/"; // the video output dir is inside the docker container.

        MutableCapabilities selenoidCapabilities = new MutableCapabilities();

       /* // Get the @DisplayName name of the test and use it as the Selenoid container name
        BrowserExtension browserExtension = new BrowserExtension(); // Create an instance
        System.out.println("name: " + browserExtension.currentDisplayName.get());
        selenoidCapabilities.setCapability("name", browserExtension.currentDisplayName.get()); // Get the displayName from the extensionContext*/


        /* How to set session timeout */
        selenoidCapabilities.setCapability("sessionTimeout", "15m");

        /* How to set timezone */
        selenoidCapabilities.setCapability("env", new ArrayList<String>() {{
            add("TZ=UTC");
        }});

        /* How to add "trash" button */
        selenoidCapabilities.setCapability("labels", new HashMap<String, Object>() {{
            put("manual", "true");
        }});
        selenoidCapabilities.setCapability("enableVNC", true);
        selenoidCapabilities.setCapability("enableVideo", false);
        selenoidCapabilities.setCapability("videoOutputDir", videoOutputDir);

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("selenoid:options", selenoidCapabilities);

        config.browserCapabilities(capabilities);
        config.fileDownload(FOLDER);
        // Create a RemoteWebDriver instance
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

        return new SelenideDriver(config);
    }
}

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