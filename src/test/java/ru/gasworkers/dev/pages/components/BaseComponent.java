package ru.gasworkers.dev.pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BaseComponent {

    protected final SelenideDriver driver;
    protected final UserRole userRole;

    public BaseComponent(RoleBrowser browser) {
        userRole = browser.getUserRole();
        driver = browser.getDriver();
    }

    protected <T> T stepWithRole(String name, Allure.ThrowableRunnable<T> runnable) {
        return Allure.step(getStepNameWithRole(name), runnable);
    }

    protected void stepWithRole(String name, Allure.ThrowableRunnableVoid runnable) {
        Allure.step(getStepNameWithRole(name), runnable);
    }

    private String getStepNameWithRole(String name) {
        return userRole + ": " + name;
    }

    public void scrollIntoButton(String button, boolean align) {
        stepWithRole("Скролл до кнопок", () -> {
            driver.$(byTagAndText("span", button)).scrollIntoView(align);
            Selenide.sleep(2000);
        });
    }


}
