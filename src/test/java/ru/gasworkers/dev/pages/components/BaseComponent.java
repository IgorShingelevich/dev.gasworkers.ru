package ru.gasworkers.dev.pages.components;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Allure;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent.AllButtonSharedComponent;

public abstract class BaseComponent {

    protected final SelenideDriver driver;
    protected final Role role;


    public BaseComponent(RoleBrowser browser) {
        role = browser.getRole();
        driver = browser.getDriver();
    }

    protected <T> T stepWithRole(String name, Allure.ThrowableRunnable<T> runnable) {
        return Allure.step(getStepNameWithRole(name), runnable);
    }

    protected void stepWithRole(String name, Allure.ThrowableRunnableVoid runnable) {
        Allure.step(getStepNameWithRole(name), runnable);
    }

    private String getStepNameWithRole(String name) {
        return role + ": " + name;
    }

}
