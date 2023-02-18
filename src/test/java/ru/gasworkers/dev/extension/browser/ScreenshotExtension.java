package ru.gasworkers.dev.extension.browser;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import ru.gasworkers.dev.helpers.Attach;
import ru.gasworkers.dev.helpers.TestIdentifierHelper;
import ru.gasworkers.dev.model.browser.RoleBrowsers;

public final class ScreenshotExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        String testIdentifier = TestIdentifierHelper.getTestIdentifier(extensionContext);
        Object object = extensionContext.getStore(BrowserExtension.NAMESPACE).get(testIdentifier);

        if (object != null) {
            RoleBrowsers browsers = (RoleBrowsers) object;
            browsers.getDrivers().stream()
                    .filter(SelenideDriver::hasWebDriverStarted)
                    .forEach(this::addAttachments);

//            for (SelenideDriver driver : browsers.getDrivers()) {
//                if (driver.hasWebDriverStarted()) {
//                    addAttachments(driver);
//                }
//            }
        }

        throw throwable;
    }

    private void addAttachments(SelenideDriver driver) {
        Allure.step("Attachments", () -> {
            Attach.screenshotAs(driver, "Last screenshot");
            Attach.browserConsoleLogs(driver);
            Attach.pageSource(driver);
        });
    }

}
