package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import model.Role;
import model.browser.RoleBrowser;
import pages.common.LoginPage;

public abstract class BaseRolePages {

    protected final RoleBrowser browser;

    @Getter private final LoginPage loginPage;

    BaseRolePages(Role role, SelenideDriver driver) {
        browser = RoleBrowser.instance(driver, role);
        loginPage = new LoginPage(browser);
    }

}
