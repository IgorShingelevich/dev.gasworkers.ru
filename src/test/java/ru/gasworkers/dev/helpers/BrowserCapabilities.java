package ru.gasworkers.dev.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@Getter
@AllArgsConstructor
public enum BrowserCapabilities {
    //    CHROME_114("chrome", "114.0"),
//    CHROME_115("chrome", "115.0"),
    CHROME_119("chrome", "119.0");
//    FIREFOX_114("firefox", "114.0"),
//    FIREFOX_115("firefox", "115.0");

    private final String browserName;
    private final String browserVersion;

    public MutableCapabilities getCapabilities() {
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        switch (this) {
//            case CHROME_114:
//            case CHROME_115:
            case CHROME_119:
                chromeOptions.addArguments("--use-fake-ui-for-media-stream");
                chromeOptions.setCapability("browserVersion", browserVersion);
                return chromeOptions;
//            case FIREFOX_114:
//            case FIREFOX_115:
//                firefoxOptions.addArguments("--use-fake-ui-for-media-stream");
//                firefoxOptions.setCapability("browserVersion", browserVersion);
//                return firefoxOptions;
            default:
                throw new EnumNotSupportedException(this);
        }
    }
}
