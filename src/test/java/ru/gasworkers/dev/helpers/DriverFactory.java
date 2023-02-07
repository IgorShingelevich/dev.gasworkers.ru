package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import ru.gasworkers.dev.browser.Browser;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru"); // ToDo вынести в конфиг
        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
//         config.headless(true);
//        config.reportsFolder("target/selenide");
        config.holdBrowserOpen(true);

        return new SelenideDriver(config);
    }

}