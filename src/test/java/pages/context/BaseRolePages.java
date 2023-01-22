package pages.context;

import com.codeborne.selenide.SelenideDriver;
import helpers.DriverManager;
import lombok.Getter;
import model.Role;
import model.browser.RoleBrowser;
import pages.common.LoginPage;

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
