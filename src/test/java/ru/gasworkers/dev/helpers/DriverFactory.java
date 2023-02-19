package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.browser.BrowserSize;

import static com.codeborne.selenide.FileDownloadMode.FOLDER;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru"); // ToDo вынести в конфиг
        config.browserSize(annotation.browserSize(BrowserSize.DEFAULT));
        config.browserPosition(annotation.browserPosition());
        config.reportsFolder("target/selenide");

//         config.headless(true);
        config.holdBrowserOpen(true);


//        config.proxyEnabled(true);
        config.fileDownload(FOLDER);


        return new SelenideDriver(config);
    }

}
