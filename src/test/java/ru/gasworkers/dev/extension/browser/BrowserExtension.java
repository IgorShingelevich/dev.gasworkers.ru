package ru.gasworkers.dev.extension.browser;

import com.codeborne.selenide.SelenideDriver;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.*;
import ru.gasworkers.dev.helpers.DriverFactory;
import ru.gasworkers.dev.helpers.TestIdentifierHelper;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.browser.RoleBrowsers;
import ru.gasworkers.dev.pages.context.BaseRolePages;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

public class BrowserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    public static final ExtensionContext.Namespace NAMESPACE = create(BrowserExtension.class);
  /*  // to get the displayName from the extensionContext
    public ThreadLocal<String> currentDisplayName = new ThreadLocal<>();*/


    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Object testInstance = extensionContext.getRequiredTestInstance();
        List<Field> fields = Arrays.stream(testInstance.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Browser.class))
                .collect(Collectors.toList());

        RoleBrowsers browsers = new RoleBrowsers();
        for (Field field : fields) {
            Browser annotation = field.getDeclaredAnnotation(Browser.class);
            RoleBrowser browser = RoleBrowser.instance(DriverFactory.getDriver(annotation, extensionContext), annotation.role());
            browsers.add(browser);

            Constructor<? extends BaseRolePages> constructor = annotation.role().getPagesObjectClass()
                    .getDeclaredConstructor(SelenideDriver.class);
            constructor.setAccessible(true);
            BaseRolePages rolePages = constructor.newInstance(browser.getDriver());

            field.setAccessible(true);
            field.set(testInstance, rolePages);
        }

        /*// Get the displayName from the extensionContext
         String displayName = extensionContext.getDisplayName();
        currentDisplayName.set(displayName);
        System.out.println("displayName: " + displayName);*/


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
        RoleBrowser browser = RoleBrowser.instance(DriverFactory.getDriver(annotation, extensionContext), annotation.role());

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

        if (browsers == null)
            return;

        for (SelenideDriver driver : browsers.getDrivers()) {
            if (driver.hasWebDriverStarted()) {
                driver.close();
            }
        }
    }

    private RoleBrowsers getFromStore(ExtensionContext extensionContext) {
        String testIdentifier = TestIdentifierHelper.getTestIdentifier(extensionContext);
        Object object = extensionContext.getStore(NAMESPACE).get(testIdentifier);
        return object == null ? null : (RoleBrowsers) object;
    }

    private void saveToStore(ExtensionContext extensionContext, RoleBrowsers browsers) {
        String testIdentifier = TestIdentifierHelper.getTestIdentifier(extensionContext);
        extensionContext.getStore(NAMESPACE).put(testIdentifier, browsers);
    }

}
