package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.gasworkers.dev.extension.browser.Browser;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;
import static io.qameta.allure.Allure.step;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru"); // ToDo вынести в конфиг
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        config.reportsFolder("target/selenide");

//         config.headless(true);
        // С удаленным запуском нельзя ставить в true!
//        config.holdBrowserOpen(true);



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
