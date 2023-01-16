package model.browser;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import model.Role;

@Getter
public final class RoleBrowser {

    public static RoleBrowser instance(SelenideDriver driver, Role role) {
        return new RoleBrowser(driver, role);
    }

    private final SelenideDriver driver;
    private final Role role;

    private RoleBrowser(SelenideDriver driver, Role role) {
        this.driver = driver;
        this.role = role;
    }

}
