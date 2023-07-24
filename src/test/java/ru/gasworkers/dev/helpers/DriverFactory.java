package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.gasworkers.dev.extension.browser.Browser;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) {
        SelenideConfig config = new SelenideConfig();
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
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--disable-dev-shm-usage");
        // Install and activate the extension
//        chromeOptions.addExtensions(new File("src/test/resources/web/eimadpbcbfnmbkopoojfekhnkhdbieeh-4.9.64-Crx4Chrome.com.crx"));
        config.browserCapabilities(chromeOptions);

//        config.proxyEnabled(true);
        config.fileDownload(FOLDER);

//        step("Запускаем тесты удаленно", () -> {
//            config.remote("http://5.161.120.34:4444/wd/hub");
//            config.browser("chrome");
//            config.browserVersion("109.0");
//
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("enableVNC", true);
//            capabilities.setCapability("enableVideo", true);
//            config.browserCapabilities(capabilities);
//        });


        return new SelenideDriver(config);
    }


}
