package ru.gasworkers.dev.model.browser;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.model.UserRole;

@Getter
public final class RoleBrowser {

    private final UserRole userRole;

    private final SelenideDriver driver;
    private RoleBrowser(SelenideDriver driver, UserRole userRole) {
        this.driver = driver;
        this.userRole = userRole;
    }

    public static RoleBrowser instance(SelenideDriver driver, UserRole userRole) {
        return new RoleBrowser(driver, userRole);
    }

}
