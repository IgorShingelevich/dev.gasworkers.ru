package helpers;

import com.codeborne.selenide.SelenideConfig;
import com.codeborne.selenide.SelenideDriver;
import extension.browser.Browser;

public final class DriverFactory {

    public static SelenideDriver getDriver(Browser annotation) {
        SelenideConfig config = new SelenideConfig();
        config.baseUrl("https://dev.gasworkers.ru"); // ToDo вынести в конфиг

        config.browserSize(annotation.browserSize());
        config.browserPosition(annotation.browserPosition());
        return new SelenideDriver(config);
    }

}
