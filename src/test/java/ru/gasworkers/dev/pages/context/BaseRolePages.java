package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import ru.gasworkers.dev.helpers.DriverManager;
import lombok.Getter;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.common.LoginPage;

public abstract class BaseRolePages {

    protected final RoleBrowser browser;

    @Getter private final LoginPage loginPage;
    @Getter private final DriverManager driverManager;

    BaseRolePages(Role role, SelenideDriver driver) {
        browser = RoleBrowser.instance(driver, role);
        loginPage = new LoginPage(browser);
        driverManager = new DriverManager(browser);
    }

}
