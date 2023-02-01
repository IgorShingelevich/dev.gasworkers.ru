package extension.browser;

import com.codeborne.selenide.SelenideDriver;
import helpers.Attach;
import helpers.DriverFactory;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import model.browser.RoleBrowsers;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.extension.*;
import pages.context.BaseRolePages;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

public class BrowserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE = create(BrowserExtension.class);
    private final String storageKey = "STORAGE KEY " + Thread.currentThread().getId();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Object testInstance = extensionContext.getRequiredTestInstance();
        List<Field> fields = Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Browser.class))
                .collect(Collectors.toList());

        RoleBrowsers browsers = new RoleBrowsers();
        for (Field field : fields) {
            Browser annotation = field.getDeclaredAnnotation(Browser.class);
            RoleBrowser browser = RoleBrowser.instance(DriverFactory.getDriver(annotation), annotation.role());
            browsers.add(browser);

            Constructor<? extends BaseRolePages> constructor = annotation.role().getPagesObjectClass()
                    .getDeclaredConstructor(SelenideDriver.class);
            constructor.setAccessible(true);
            BaseRolePages rolePages = constructor.newInstance(browser.getDriver());

            field.setAccessible(true);
            field.set(testInstance, rolePages);
        }

        if (!browsers.isEmpty())
            saveToStore(extensionContext, browsers);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        Browser annotation = parameterContext.getParameter().getAnnotation(Browser.class);
        return annotation != null &&
                parameterContext.getParameter().getType().isAssignableFrom(annotation.role().getPagesObjectClass());
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        Browser annotation = parameterContext.getParameter().getAnnotation(Browser.class);
        RoleBrowser browser = RoleBrowser.instance(DriverFactory.getDriver(annotation), annotation.role());

        RoleBrowsers browsersFromStore = getFromStore(extensionContext);
        if (browsersFromStore == null)
            browsersFromStore = new RoleBrowsers();
        browsersFromStore.add(browser);
        saveToStore(extensionContext, browsersFromStore);

        Constructor<? extends BaseRolePages> constructor = annotation.role().getPagesObjectClass()
                .getDeclaredConstructor(SelenideDriver.class);
        constructor.setAccessible(true);
        return constructor.newInstance(browser.getDriver());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        RoleBrowsers browsers = getFromStore(extensionContext);
        clearStore(extensionContext);

        if (browsers == null)
            return;

        for (SelenideDriver driver : browsers.getDrivers()) {
            if (driver.hasWebDriverStarted()) {
                Allure.step("Attachments", () -> {
                    Attach.screenshotAs(driver, "Last screenshot");
                    Attach.browserConsoleLogs(driver);
                    Attach.pageSource(driver);
                });
                driver.close();
            }
        }
    }

    private RoleBrowsers getFromStore(ExtensionContext extensionContext) {
        Object object = extensionContext.getStore(NAMESPACE).get(storageKey);
        return object == null ? null : (RoleBrowsers) object;
    }

    private void saveToStore(ExtensionContext extensionContext, RoleBrowsers browsers) {
        extensionContext.getStore(NAMESPACE).put(storageKey, browsers);
    }

    private void clearStore(ExtensionContext extensionContext) {
        extensionContext.getStore(NAMESPACE).remove(storageKey);
    }

}
