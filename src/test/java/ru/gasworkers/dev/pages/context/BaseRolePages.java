package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.helpers.DriverManager;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.sharedPages.LoginPage;

public abstract class BaseRolePages {

    protected final RoleBrowser browser;

    @Getter private final LoginPage loginPage;
    @Getter private final DriverManager driverManager;

    BaseRolePages(UserRole userRole, SelenideDriver driver) {
        browser = RoleBrowser.instance(driver, userRole);
        loginPage = new LoginPage(browser);
        driverManager = new DriverManager(browser);
    }

}
