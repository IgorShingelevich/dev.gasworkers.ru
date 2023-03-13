package ru.gasworkers.dev.helpers;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.SessionId;

import java.nio.charset.StandardCharsets;

import static org.openqa.selenium.logging.LogType.BROWSER;

public final class Attach {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(SelenideDriver driver, String attachName) {
        return ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
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
    private static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo(SelenideDriver driver) {
        SessionId sessionId = driver.getSessionId();
        String videoUrl = "http://5.161.120.34:8080/video/" + sessionId + ".mp4";
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + videoUrl
                + "' type='video/mp4'></video></body></html>";
    }

}