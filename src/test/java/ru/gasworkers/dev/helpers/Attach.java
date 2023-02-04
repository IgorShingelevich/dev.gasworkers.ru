package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.logging.LogType.BROWSER;

public final class Attach {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(SelenideDriver driver, String attachName) {
        return ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource(SelenideDriver driver) {
        return driver.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    public static void browserConsoleLogs(SelenideDriver driver) {
        attachAsText(
                "Browser console logs",
                String.join("\n", driver.getWebDriverLogs().logs(BROWSER))
        );
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

}